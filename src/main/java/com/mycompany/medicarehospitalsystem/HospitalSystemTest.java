import com.mycompany.medicarehospitalsystem.PatientCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class HospitalSystemTest { // start of class

    PatientManagement patientManagement;
    BedManagement bedManagement;

    @BeforeEach
    public void setUp() { // opening curly brace
        patientManagement = new PatientManagement();
        bedManagement = new BedManagement();
    } // closing of curly brace

    @Test
    public void testRegisterPatient() { // opening curly brace
        Patient patient = new Patient("P001", "John", "Smith", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        boolean result = patientManagement.registerPatient(patient);
        assertTrue(result);
        assertEquals(1, patientManagement.getTotalPatients());
    } // closing of curly brace

    @Test
    public void testPreventDuplicatePatientId() { // opening curly brace
        Patient patient1 = new Patient("P001", "John", "Smith", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        Patient patient2 = new Patient("P001", "Jane", "Doe", 25, "Female", "Cold", PatientCategory.OUTPATIENT);
        patientManagement.registerPatient(patient1);
        boolean result = patientManagement.registerPatient(patient2);
        assertFalse(result);
        assertEquals(1, patientManagement.getTotalPatients());
    } // closing of curly brace

    @Test
    public void testSearchPatient() { // opening curly brace
        Patient patient = new Patient("P002", "Mary", "Jones", 40, "Female", "Diabetes", PatientCategory.OUTPATIENT);
        patientManagement.registerPatient(patient);
        Patient found = patientManagement.findPatient("P002");
        assertNotNull(found);
        assertEquals("Mary", found.getFirstName());
    } // closing of curly brace

    @Test
    public void testSearchPatientNotFound() { // opening curly brace
        Patient found = patientManagement.findPatient("P999");
        assertNull(found);
    } // closing of curly brace

    @Test
    public void testUpdatePatientDetails() { // opening curly brace
        Patient patient = new Patient("P003", "Tom", "Brown", 50, "Male", "Asthma", PatientCategory.OUTPATIENT);
        patientManagement.registerPatient(patient);
        boolean result = patientManagement.updatePatient("P003", "Tom", "Brown", 51, "Male", "Severe Asthma");
        assertTrue(result);
        assertEquals(51, patientManagement.findPatient("P003").getAge());
        assertEquals("Severe Asthma", patientManagement.findPatient("P003").getMedicalCondition());
    } // closing of curly brace

    @Test
    public void testDeletePatient() { // opening curly brace
        Patient patient = new Patient("P004", "Sam", "White", 35, "Male", "Migraine", PatientCategory.OUTPATIENT);
        patientManagement.registerPatient(patient);
        boolean result = patientManagement.deletePatient("P004");
        assertTrue(result);
        assertEquals(0, patientManagement.getTotalPatients());
    } // closing of curly brace

    @Test
    public void testAllocateBed() { // opening curly brace
        Inpatient patient = new Inpatient("P005", "Lisa", "Green", 28, "Female", "Surgery Recovery", "W1");
        patientManagement.registerPatient(patient);
        boolean result = bedManagement.allocateBed("B01", "P005");
        assertTrue(result);
        assertTrue(bedManagement.findBed("B01").isOccupied());
    } // closing of curly brace

    @Test
    public void testReleaseBed() { // opening curly brace
        Inpatient patient = new Inpatient("P006", "Kevin", "Black", 45, "Male", "Fracture", "W1");
        patientManagement.registerPatient(patient);
        bedManagement.allocateBed("B02", "P006");
        String releasedPatientId = bedManagement.releaseBed("B02");
        assertEquals("P006", releasedPatientId);
        assertFalse(bedManagement.findBed("B02").isOccupied());
    } // closing of curly brace

    @Test
    public void testPreventAllocatingOccupiedBed() { // opening curly brace
        Inpatient patient1 = new Inpatient("P007", "Anna", "Grey", 33, "Female", "Pneumonia", "W1");
        Inpatient patient2 = new Inpatient("P008", "Ben", "Reed", 60, "Male", "Stroke", "W1");
        patientManagement.registerPatient(patient1);
        patientManagement.registerPatient(patient2);
        bedManagement.allocateBed("B03", "P007");
        boolean result = bedManagement.allocateBed("B03", "P008");
        assertFalse(result);
    } // closing of curly brace

    @Test
    public void testPreventBedAllocationWhenFull() { // opening curly brace
        for (int i = 1; i <= 20; i++) {
            Inpatient patient = new Inpatient("P" + i, "First" + i, "Last" + i, 20, "Male", "Condition", "W1");
            patientManagement.registerPatient(patient);
            String bedLabel;
            if (i < 10) {
                bedLabel = "B0" + i;
            } else {
                bedLabel = "B" + i;
            }
            bedManagement.allocateBed(bedLabel, "P" + i);
        } // closing of curly brace
        
        assertFalse(bedManagement.hasAvailableBed());

        boolean result = bedManagement.allocateBed("B01", "P021");
        assertFalse(result);
    } // closing of curly brace

    @Test
    public void testSortPatientsBySurname() { // opening curly brace
        patientManagement.registerPatient(new Patient("P010", "Zack", "Young", 20, "Male", "Cold", PatientCategory.OUTPATIENT));
        patientManagement.registerPatient(new Patient("P011", "Amy", "Adams", 22, "Female", "Cold", PatientCategory.OUTPATIENT));
        patientManagement.registerPatient(new Patient("P012", "Mike", "Lewis", 25, "Male", "Cold", PatientCategory.OUTPATIENT));

        patientManagement.sortPatientsBySurname();

        assertEquals("Adams", patientManagement.patients.get(0).getLastName());
        assertEquals("Lewis", patientManagement.patients.get(1).getLastName());
        assertEquals("Young", patientManagement.patients.get(2).getLastName());
    } // closing of curly brace

    @Test
    public void testSortPatientsById() { // opening curly brace
        patientManagement.registerPatient(new Patient("P030", "A", "One", 20, "Male", "Cold", PatientCategory.OUTPATIENT));
        patientManagement.registerPatient(new Patient("P010", "B", "Two", 22, "Female", "Cold", PatientCategory.OUTPATIENT));
        patientManagement.registerPatient(new Patient("P020", "C", "Three", 25, "Male", "Cold", PatientCategory.OUTPATIENT));

        patientManagement.sortPatientsById();

        assertEquals("P010", patientManagement.patients.get(0).getPatientId());
        assertEquals("P020", patientManagement.patients.get(1).getPatientId());
        assertEquals("P030", patientManagement.patients.get(2).getPatientId());
    } // closing of curly brace
} // closing of curly brace