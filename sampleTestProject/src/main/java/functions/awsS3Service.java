//package functions;
//
//import java.nio.file.Paths;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//public class awsS3Service {
//	private static final String REGION = System.getProperty("aws.region", "us-east-1");
//
//	private static String bucketName = "ipc-bucket";
//	// private static String accessKey = "your-aws-access-key-here";
//	// private static String secretKey = "your-aws-secret-key-here";
//
//	public static void main(String[] args) {
//		String filePath = "C:\\Code\\infor-cqa-libraries\\sampleTestProject\\artefact\\TCLN_ContextPassing2.xls";
//		String keyName = "TCLN_ContextPassing2.xls";
//		S3Client s3Client;
//
//		if (accessKey != null && !accessKey.isEmpty() && secretKey != null && !secretKey.isEmpty()) {
//
//			// Local development: Use static credentials
//			s3Client = S3Client.builder().region(Region.US_EAST_1).credentialsProvider(
//					StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))).build();
//		} else {
//			// EC2: Use IAM role from instance profile
//			s3Client = S3Client.builder().region(Region.US_EAST_1)
//					.credentialsProvider(DefaultCredentialsProvider.create()).build();
//		}
//		uploadFile(s3Client, bucketName, keyName, filePath);
//		s3Client.close();
//	}
//
//	private static void uploadFile(S3Client s3, String bucketName, String keyName, String filePath) {
//		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(keyName).build();
//
//		s3.putObject(putObjectRequest, RequestBody.fromFile(Paths.get(filePath)));
//		System.out.println("File uploaded successfully. URL: " + getFileUrl(bucketName, keyName));
//	}
//
//	private static String getFileUrl(String bucketName, String keyName) {
//		return "https://" + bucketName + ".s3." + REGION + ".amazonaws.com/" + keyName;
//	}
//}