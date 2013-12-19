package Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ICTCLAS.I3S.AC.ICTCLAS50;

public class BaseWordCut {
	
	/**
	 * ����ִʣ�����item:value��hashmap
	 * @param content
	 * @return
	 */
	public HashMap<String, Integer> doCutWord(String content){
		HashMap<String, Integer> resultMap = new HashMap<String,Integer>();
		try {
			ICTCLAS50 ictclas50 = new ICTCLAS50();
			String argu = ".";
			if (ictclas50.ICTCLAS_Init(argu.getBytes("gbk"))==false) {
				System.out.println("init false");
			}else{
//				System.out.println("init true");
			}
			
			String userdict = "userdict.txt";
			byte[] nativeBytes1 = ictclas50.ICTCLAS_ParagraphProcess(content.getBytes("gbk"), 0, 1);
			String nativeStr1 = new String(nativeBytes1);
			
			Pattern pattern = Pattern.compile("( ([^ ])*?)(/n(\\w)*) ");
			Matcher matcher = pattern.matcher(nativeStr1);	
			while (matcher.find()) {
				String word = matcher.group(1).trim();
				if(resultMap.containsKey(word)){//��������ڸ������Ӹ�����Ѵ�Ƶ��Ϊ1
					resultMap.put(word, resultMap.get(word)+1);
				}else{//����Ѿ����ڸ�����Ƶ+1
					resultMap.put(word, 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * ���ļ��м��ط���������Ϣ�����ظ�ʽ�� �����=>����
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public HashMap<String, Integer> loadClassFromFile(File file) throws IOException{
		HashMap<String, Integer> result = new HashMap<String,Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String temp = null;
		while((temp = reader.readLine()) != null){
			String[] str = temp.split(" ");
			result.put(str[1], Integer.parseInt(str[0]));
			System.out.println(str[1] + " " + str[0]);
		}
		return result;
	}

}
