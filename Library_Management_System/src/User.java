
abstract class User {
	int idnum;
	String name;
	String phonenum;
	int grade = 0; //�г�(1,2,3,4 �г� / ���п� �� ������ ���� ������ ������ : 5 / ���ӱ��� : 6 )
	int lend = 0; //�������� 1 2 3
	
	/*
	 * ���л� : �������
	 * �кλ� : ���� 5��/�Ⱓ 10��/���� 3�� 
	 * ���п��� & �� ����: ���� 10��/�Ⱓ 30��/���� 5��
	 * ���ӱ��� : 20��/ 90��/ 5��
	 */
	
	User(){
		boolean lengthTest = true;
		while(lengthTest) {
			idnum = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("���� ID ��ȣ�� �Է��ϼ��� "));
			if(idnum > 999 || idnum < 1) {
				System.out.println("ID ��ȣ�� ������ 1 ~ 999 �����Դϴ�.");
			}
			else
				lengthTest = false;
			}
			
		lengthTest = true;
		while(lengthTest) {
			name = javax.swing.JOptionPane.showInputDialog("�̸��� �Է��ϼ��� ");
			if(name.length()>10) {
				System.out.println("�̸��� ���̴� 10byte �����Դϴ�..");
			}
			else
				lengthTest = false;
		}
		
		lengthTest = true;
		while(lengthTest) {
			phonenum = javax.swing.JOptionPane.showInputDialog("����ó�� �Է��ϼ���.(010-0000-0000)");
			if(phonenum.length() > 13) {
				System.out.println("����ó�� ����� ���� �ٽ� �Է����ּ���. (010-1234-1234)");
			}
			else
				lengthTest = false;
		}
	}
	
	User(byte [] data){
		String temp="";	
		char c;
		for(int i = 0; i <= 28; i++) {
			if((c = (char)data[i]) == '*' && i != 28)
				continue;
			switch(i) {
				case 3:
					idnum = Integer.parseInt(temp);
					temp = "";
					break;
				case 13:
					name = temp;
					temp = ""; 
					break;
				case 26:
					phonenum = temp;
					temp ="";
					break;
				case 27:
					grade = Integer.parseInt(temp);
					temp ="";
					break;
				case 28:	
					lend = Integer.parseInt(temp);
					temp ="";
					break;
			}
			temp += c;
		}
	}
	
	void printuser() {
		System.out.println("idnum : " + idnum);
		System.out.println("name : " + name);
		System.out.println("phonenum : " + phonenum);
		System.out.println("grade : " + grade);
		System.out.println("lend : " + lend);
		if(this instanceof Student) {
			Student stu = (Student)this;
			System.out.println("leave : " + stu.leave);
			System.out.println("User type : Student");
		}
		else
			System.out.println("User type : Faculty");
		System.out.println("");
	}
	
	byte [] tobyte() {
		byte [] record = new byte [50];
		
		for(int i = 0; i < 3;i++)
			if((idnum + "").length() > i)
				record[i] = (idnum + "").getBytes()[i];
			else
				record[i] = '*';
		
		for(int i = 3; i < 13;i++)
			if(name.length() > i-3)
				record[i] = name.getBytes()[i-3];
			else
				record[i] = '*';
		
		for(int i = 13; i < 26;i++)
			if(phonenum.length() > i-13)
				record[i] = phonenum.getBytes()[i-13];
			else
				record[i] = '*';
		
		record[26] = (grade + "").getBytes()[0];
		record[27] = (lend + "").getBytes()[0];
		
		for(int i = 28; i < 50; i++) 
			record[i] = '*';
		
		if(this instanceof Student) {
			Student stu = (Student)this;
			if(stu.leave) 
				record[28] = '1';
			else
				record[28] = '0';
		}
		
		return record;
	}

}
