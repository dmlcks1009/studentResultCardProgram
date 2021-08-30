package studentResultCardProgram;

import java.util.Objects;

public class StudentResult {
	private String studentNum;
	private String studnetName;
	private int korScore;
	private int mathScore;
	private int engScore;
	private int total;
	private double avg;
	private String grade;

	@Override
	public int hashCode() {
		return Objects.hash(studentNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StudentResult) {
			StudentResult studentNum = (StudentResult) obj;
			return this.studentNum.equals(studentNum.getStudentNum());
		}
		return false;
	}

	public StudentResult(String studentNum, String studnetName, int korScore, int mathScore, int engScore, int total,
			double avg, String grade) {
		super();
		this.studentNum = studentNum;
		this.studnetName = studnetName;
		this.korScore = korScore;
		this.mathScore = mathScore;
		this.engScore = engScore;
		this.total = total;
		this.avg = avg;
		this.grade = grade;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudnetName() {
		return studnetName;
	}

	public void setStudnetName(String studnetName) {
		this.studnetName = studnetName;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "학번 : " + studentNum + "\t이름 : " + studnetName + "\t국어점수 : " + korScore + "\t수학점수 : " + mathScore
				+ "\t영어점수 : " + engScore + "\t총합 : " + total + "\t평균 : " + String.format("%.2f", this.avg) + "\t등급 : "
				+ grade;
	}

}
