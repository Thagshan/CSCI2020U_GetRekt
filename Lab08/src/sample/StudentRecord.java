package sample;

/**
 * Created by jesse on 06/02/17.
 */
public class StudentRecord {
    private String ID;
    private float midterm;
    private float assignments;
    private float exam;
    private float finalGrade;
    private char grade;

    public StudentRecord(String id, float midterm, float assignments,
                         float exam) {
        this.setID(id);
        this.setMidterm(midterm);
        this.setAssignments(assignments);
        this.setExam(exam);
        calcGrade(this.getMidterm(), this.getAssignments(), this.getExam());
    }

    public StudentRecord(String id, float midterm, float assignments,
                         float exam, float finalGrade, char grade) {
        this.setID(id);
        this.setMidterm(midterm);
        this.setAssignments(assignments);
        this.setExam(exam);
        this.finalGrade = finalGrade;
        this.setGrade(grade);
    }

    private void calcGrade(float midterm, float assignments, float exam) {
        float grade = (assignments*0.2f)+(midterm*0.3f)+(exam*0.5f);
        this.finalGrade = grade;
        if(grade < 50.0f) {
            this.setGrade('F');
        } else if(grade > 49.0f && grade < 60.0f) {
            this.setGrade('D');
        } else if(grade > 59.0f && grade < 70.0f) {
            this.setGrade('C');
        } else if(grade > 69.0f && grade < 80.0f) {
            this.setGrade('B');
        } else if(grade > 89.0f && grade < 100.0f) {
            this.setGrade('A');
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getExam() {
        return exam;
    }

    public void setExam(float exam) {
        this.exam = exam;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
