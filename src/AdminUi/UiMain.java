package AdminUi;

import java.io.IOException;
import java.sql.Date;

import svmHelper.svm_predict;
import svmHelper.svm_train;

public class UiMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException{
		String[] arg = {"-t","0","trainfile/svm.train","trainfile/svm.model"};
		String[] parg = {"testfile/svm.test","trainfile/svm.model","testfile/result.txt"};
		
		System.out.println("ѵ����ʼ");
		svm_train train = new svm_train();
		svm_predict predict = new svm_predict();
		train.main(arg);
		System.out.println("ѵ������");
		System.out.println("���࿪ʼ");
		predict.main(parg);
		System.out.println("�������");
		
	}
}
