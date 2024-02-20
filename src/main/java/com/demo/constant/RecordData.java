package com.demo.constant;

public enum RecordData {
	宜蘭縣(0), 花蓮縣(1),臺東縣(2),澎湖縣(3),金門縣(4),連江縣(5),臺北市(6),
	新北市(7),桃園市(8),臺中市(9),臺南市(10),高雄市(11),基隆市(12),新竹縣(13),新竹市(14)
	,苗栗縣(15),彰化縣(16),南投縣(17),雲林縣(18),嘉義縣(19),嘉義市(20),屏東縣(21);  

	int value;

	RecordData(int data){  
	  
		value=data;  
	    }

	int getName() {

		return value;
	}
}
