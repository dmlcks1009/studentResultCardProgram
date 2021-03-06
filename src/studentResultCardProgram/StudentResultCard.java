package studentResultCardProgram;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class StudentResultCard {	
	public static final int INPUT_DATA = 1,INQUIRY_DATA = 2,UPDATE_DATA = 3,DELETE_DATA = 4, SEARCH_DATA = 5, SORT_DATA = 6,WASTEBASKET_DATA = 7,FINISH = 8;
	public static final Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException{
		
		boolean flag = false;
		int selectNumber = 0;
		//select menu
		while(!flag) {
			selectNumber = showChoiceMenu();
			
			switch(selectNumber) {
			//학생 정보 입력
			case INPUT_DATA: studentResultInput(); break; 
			//학생 정보 조회
			case INQUIRY_DATA: studentResultInquiry(); break;
			//학생 정보 수정
			case UPDATE_DATA: studentResultUpdate(); break; 
			//학생 정보 삭제
			case DELETE_DATA: studentResultDelete(); break; 
			//학생 정보 검색
			case SEARCH_DATA: studentResultSearch(); break;
			//학생 정보 정렬
			case SORT_DATA: studentResultSort(); break; 
			//삭제된 학생 정보
			case WASTEBASKET_DATA : studentResultWastebasket(); break; 
			//프로그램 종료
			case FINISH: flag = true; break; 
			default : System.out.println("입력가능한 숫자 범위를 벗어났습니다. 다시 입력해주세요."); break;
			}//end of switch
		}//end of while
		System.out.println("프로그램이 종료되었습니다.");
	}//end of main
	//메인 선택 메뉴
	private static int showChoiceMenu() {
		int selectNumber = 0;
		boolean flag = false;
		while(!flag) {
			System.out.print("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("\n1.학생 정보 입력 2.학생 정보 조회 3.학생 정보 수정 4.학생 정보 삭제 5.학생 정보 검색 6.학생 정보 정렬 7.삭제한 학생 정보 8.프로그램 종료");
			System.out.print("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("\n번호선택>>");
			try {
				selectNumber = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e){
				System.out.println("숫자만 입력해주세요.");
				continue;
			}catch(Exception e) {
				System.out.println("문제발생, 다시 입력해주세요.");
				continue;
			}
			break;
		}
		return selectNumber;
	}
	//학생 정보 입력
	private static void studentResultInput() {
		boolean flag = false;
		String studentNum = null;
		String studentName = null;
		int korScore = 0;
		int mathScore = 0;
		int engScore = 0;
		int total = 0;
		double avg = 0.0;
		String grade = null;
		while(true) {
			System.out.print("학생번호(8자리)를 입력>>");
			studentNum = scan.nextLine();
			if(studentNum.length() != 8) {
				System.out.println("학생번호 양식을 지켜주세요.");
				continue;
			}
			boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
			if(checkStudentNum == true) {
				System.out.println("존재하는 학생번호입니다. 다시 입력해주세요.");
				continue;
			}break;
		}//end of while
			while(true) {
			System.out.print("학생 이름 입력>>");
			studentName = scan.nextLine();
			boolean inputCheck = Pattern.matches("^[가-힣]*$", studentName);
			if(inputCheck == true) {
			}else {
				System.out.println("한글만 입력해주세요");
				continue;
			} 
			if(studentName.length() > 4 || studentName.length() < 2) {
				System.out.println("이름입력 양식을 지켜주세요.");
				continue;
			}else {
	               break; 
	            }
			}//end of while
			while(true) {
				System.out.print("국어점수 입력>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// 정수 입력
				}
			     catch(Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
			     }
				if(korScore < 0 || korScore >100) {
					System.out.println("점수입력 범위를 지켜주세요.");
					continue;
				}else {
					break;
				}
			}//end of while
			while(true) {
				System.out.print("수학점수 입력>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// 정수 입력
				}
			     catch(Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
			     }
				if(mathScore < 0 || mathScore >100) {
					System.out.println("점수입력 양식을 지켜주세요.");
					continue;
				}else {
					break;
				}
			}//end of while
			while (true) {
				System.out.print("영어점수 입력>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// 정수 입력
				} catch (Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("점수입력 양식을 지켜주세요.");
					continue;
				}
				break;
			} // end of while
			StudentResult studentResult = new StudentResult(studentNum, studentName, korScore, mathScore, engScore,
					total, avg, grade);
			int count = StudentResultController.studentResultInputTBL(studentResult);

			if (count == 1) {
				System.out.println(studentResult.getStudentNum() + "님 삽입성공");
			} else {
				System.out.println(studentResult.getStudentNum() + "님 삽입실패");
			}
		}

		// 학생 번호 중복값 허용 X
		private static boolean duplicateStudentNumCheck(String studentNum) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNumNotOverlapTBL(studentNum);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// 학생 정보 조회
		private static void studentResultInquiry() {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultInquiryTBL();
			if (list.size() <= 0) {
				System.out.println("출력할 데이터가 없습니다.");
				return;
			}
		}

		// 학생 정보 수정
		private static void studentResultUpdate() {
			int korScore = 0;
			int mathScore = 0;
			int engScore = 0;
			String studentNum = null;
			while (true) {
				System.out.print("수정할 학생번호(8자리)를 입력>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("학생번호 양식을 지켜주세요.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum == false) {
					System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");
					continue;
				}
				break;
			} // end of while
			while (true) {
				System.out.print("국어점수 수정>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// 정수 입력
				} catch (Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
				}
				if (korScore < 0 || korScore > 100) {
					System.out.println("점수입력 범위를 지켜주세요.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("수학점수 수정>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// 정수 입력
				} catch (Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
				}
				if (mathScore < 0 || mathScore > 100) {
					System.out.println("점수입력 범위를 지켜주세요.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("영어점수 수정>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// 정수 입력
				} catch (Exception e) {
					System.out.println("숫자가 아닙니다. 다시 입력!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("점수입력 범위를 지켜주세요.");
					continue;
				} else {
					break;
				}
			} // end of while
			int count = StudentResultController.studentResultUpdateTBL(studentNum, korScore, mathScore, engScore);
			if (count != 0) {
				System.out.println(studentNum + "번 학생 수정성공");
			} else {
				System.out.println(studentNum + "번 학생 수정실패");
			}
		}

		// 학생 정보 삭제
		private static void studentResultDelete() {
			String studentNum = null;
			while (true) {
				System.out.print("삭제할 학생의 학생번호(8자리)를 입력>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("학생번호 양식을 지켜주세요.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum != true) {
					System.out.println("존재하지않는 학생번호입니다. 다시 입력해주세요.");
					continue;
				}
				break;
			} // end of while
			boolean flag = false;
			int count = StudentResultController.studentResultDeleteTBL(studentNum);
			if (count == 1) {
				flag = true;
				System.out.println(studentNum + "번 학생 삭제완료했습니다.");
			} else {
				System.out.println(studentNum + "번 학생 삭제실패했습니다.");
			}
		}

		// 학생 정보 검색
		private static void studentResultSearch() {
			// 검색할 내용 입력받기(검색할 조건을 개개인이 부여하기.)
			boolean flag = false;
			int selcetNumber = 0;
			final int NUM_SEARCH = 1, NAME_SEARCH = 2, SEARCH_FINISH = 3;
			String studentNum = null;
			String studentName = null;

			while (!flag) {
				// 매뉴출력및 번호선택

				selcetNumber = displayStudentResultSearchMenu();
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				boolean checkStudentName = duplicateStudentNameCheck(studentName);

				switch (selcetNumber) {
				case NUM_SEARCH:
					System.out.print("검색할 학생학번을 입력해주세요>>");
					studentNum = scan.nextLine();
					if (studentNum.length() != 8) {
						System.out.println("학생번호 양식을 지켜주세요.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("존재하지않는 학생번호입니다. 다시 입력해주세요.");
						continue;
					}
					StudentResultController.studentNUM_SEARCH(studentNum);
					break;
				case NAME_SEARCH:
					System.out.print("검색할 학생이름을 입력해주세요>>");
					studentName = scan.nextLine();
					if (studentName.length() > 4 || studentName.length() < 2) {
						System.out.println("이름입력 양식을 지켜주세요.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("존재하지않는 학생이름입니다. 다시 입력해주세요.");
						continue;
					}
					StudentResultController.studentNAME_SEARCH(studentName);
					break;
				case SEARCH_FINISH:
					flag = true;
					break;
				default:
					System.out.println("번호가 초과되었습니다");
					break;
				}// end of switch
			}

		}

		// 학생 정보 검색 시 잘못된 이름 검색 허용X
		private static boolean duplicateStudentNameCheck(String studentName) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNameNotOverlapTBL(studentName);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// 검색 방식 표현 메뉴
		private static int displayStudentResultSearchMenu() {
			int selectNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.print("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.print("\n검색선택 1.학생번호부검색 2.이름검색 3.나가기");
				System.out.print("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.print("\n번호선택>>");
				try {
					selectNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("숫자 입력범위를 초과하였습니다.");
					continue;
				} catch (Exception e) {
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				break;
			}
			return selectNumber;
		}

		// 학생 정보 정렬
		private static void studentResultSort() {
			boolean flag = false;
			int selcetNumber = 0;
			final int STUDENT_NUM_ASC = 1, STUDENT_AVG_DESC = 2, FINISH = 3;
			boolean numberInputContitue = false;
			while (!flag) {
				// 매뉴출력및 번호선택

				selcetNumber = displayShowMenu();

				switch (selcetNumber) {
				case STUDENT_NUM_ASC:
					StudentResultController.studentNum_ASC();
					break;
				case STUDENT_AVG_DESC:
					StudentResultController.studentAvg_DESC();
					break;
				case FINISH:
					flag = true;
					break;
				default:
					System.out.println("번호가 초과되었습니다");
					break;
				}// end of switch
			}

		}

		// 정렬 방식 표현 메뉴
		private static int displayShowMenu() {
			int selcetNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("1.학번정렬 2.평균점수 정렬 3.나가기");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.print("번호선택>>");
				try {
					// 번호선택
					selcetNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("숫자입력요망");
					continue;
				} catch (Exception e) {
					System.out.println("숫자입력요망 재입력요망");
					continue;
				}
				break;
			}
			return selcetNumber;

		}

		// 삭제된 학생 정보
		private static void studentResultWastebasket() {
			List<StudentResult> list = new ArrayList<StudentResult>();
			list = StudentResultController.studentResultWastebasketTBL();
		}

	}