
public class Faculty extends User {
	Faculty(){
		super();
		setgrade();
		
	}
	Faculty(byte [] data){
		super(data);		
	}
	void setgrade() {
		boolean lengthTest = true;
		while(lengthTest) {
			grade = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("������ ���� ������ ������ : '5' / ���ӱ��� : '6'"));
			if(grade > 6 || grade < 5) {
				System.out.println("�ùٸ� ���� �Է��ϼ���.");
			}
			else
				lengthTest = false;
			}
	}
}
