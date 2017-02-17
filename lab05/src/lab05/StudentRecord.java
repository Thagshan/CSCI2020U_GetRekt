package lab05;

public class StudentRecord {
    private String sid;
    private float assignments;
    private float midterm;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String sid, float assignments, float midterm, float finalExam) {
        this.sid = sid;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;

        // Calculate mark
        finalMark = assignments * 0.2f + midterm * 0.3f + finalExam * 0.5f;

        // Calculate letter grade
        if (finalMark < 50.0f) {
            letterGrade = "F";
        } else if (finalMark < 60) {
            letterGrade = "D";
        } else if (finalMark < 70) {
            letterGrade = "C";
        } else if (finalMark < 80) {
            letterGrade = "B";
        } else {
            letterGrade = "A";
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}
