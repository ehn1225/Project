
public class Student extends User{
	boolean leave; //���п���
	
	Student(){
		super();
		setgrade();
		setleave();
	}
	
	Student(byte [] data){
		super(data);
		if((char)data[28] == '1') 
			leave = true;			
		else
			leave = false;
	}
		
	void setgrade() {
		boolean lengthTest = true;
		while(lengthTest) {
			grade = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("�г��� �Է��ϼ���(���п��� : '5')"));
			if(grade > 5 || grade < 1) {
				System.out.println("�г��� ������ 1 ~ 5 �����Դϴ�.");
			}
			else
				lengthTest = false;
			}
	}
	
	void setleave() {
		boolean lengthTest = true;
		while(lengthTest) {
			int a = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("���п���(0 : ���л� , 1 : ���л�)"));
			if(a != 0 && a != 1) {
				System.out.println("���л��̸� '0', ���л��̸� '1'�� �Է��ϼ���.");
			}
			else {
				lengthTest = false;
				if(a == 1) 
					leave = true;
				else
					leave = false;
			}
		}		
	}


}
