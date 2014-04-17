package cn.mytaobao.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


import java.util.Enumeration;



/**
 * 
 * ����������
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:46:11 AM
 * @version 1.0
 */
public class FileUtil {

	/**
	 * �����������г�ĳ�ļ��м������ļ���������ļ������ɸ�����չ������
	 * 
	 * @param path
	 *            �ļ���
	 */
	public static void list(File path) {
		if (!path.exists()) {
			System.out.println("�ļ����Ʋ�����!");
		} else {
			if (path.isFile()) {
				if (path.getName().toLowerCase().endsWith(".pdf")
						|| path.getName().toLowerCase().endsWith(".doc")
						|| path.getName().toLowerCase().endsWith(".chm")
						|| path.getName().toLowerCase().endsWith(".html")
						|| path.getName().toLowerCase().endsWith(".htm")) {// �ļ���ʽ
					System.out.println(path);
					System.out.println(path.getName());
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					list(files[i]);
				}
			}
		}
	}
	
	

	/**
	 * ��������������һ��Ŀ¼�����ļ���ָ��·���£�����Դ�ļ�������Ŀ���ļ�·����
	 * 
	 * @param source
	 *            Դ�ļ�
	 * @param target
	 *            Ŀ���ļ�·��
	 * @return void
	 */
	public static void copy(File source, File target) {
		File tarpath = new File(target, source.getName());
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				copy(dir[i], tarpath);
			}
		} else {
			try {
				InputStream is = new FileInputStream(source); // ���ڶ�ȡ�ļ���ԭʼ�ֽ���
				OutputStream os = new FileOutputStream(tarpath); // ����д���ļ���ԭʼ�ֽڵ���
				byte[] buf = new byte[1024];// �洢��ȡ���ݵĻ�������С
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**

     * �����ļ����е������ļ�������һ���ļ���

     * @param srcDirector Դ�ļ���

     * @param desDirector Ŀ���ļ���

     */

    public static void copyFileWithDirector(String srcDirector, String desDirector) throws IOException {



        (new File(desDirector)).mkdirs();

        File[] file = (new File(srcDirector)).listFiles();

        for (int i = 0; i < file.length; i++) {

            if (file[i].isFile()) {

               // log.debug("������" + file[i].getAbsolutePath() + "-->" + desDirector + "/" + file[i].getName());

                FileInputStream input = new FileInputStream(file[i]);

                FileOutputStream output = new FileOutputStream(desDirector + "/" + file[i].getName());

                byte[] b = new byte[1024 * 5];

                int len;

                while ((len = input.read(b)) != -1) {

                    output.write(b, 0, len);

                }

                output.flush();

                output.close();

                input.close();

            }

            if (file[i].isDirectory()) {

               // log.debug("������" + file[i].getAbsolutePath() + "-->" + desDirector + "/" + file[i].getName());

                copyFileWithDirector(srcDirector + "/" + file[i].getName(), desDirector + "/" + file[i].getName());

            }

        }

    }



	/**
	 * 
	 * ɾ���ļ���
	 * 
	 * @param folderPath
	 *            folderPath �ļ�����������·��
	 */

	public static void delFolder(String folderPath) throws Exception {

		// ɾ����������������

		delAllFile(folderPath);

		String filePath = folderPath;

		filePath = filePath.toString();

		File myFilePath = new File(filePath);

		// ɾ�����ļ���

		myFilePath.delete();

	}

	/**
	 * 
	 * ɾ��ָ���ļ����������ļ�
	 * 
	 * @param path
	 *            �ļ�����������·��
	 */

	public static boolean delAllFile(String path) throws Exception {

		boolean flag = false;

		File file = new File(path);

		if (!file.exists()) {

			return flag;

		}

		if (!file.isDirectory()) {

			return flag;

		}

		String[] tempList = file.list();

		File temp = null;

		for (int i = 0; i < tempList.length; i++) {

			if (path.endsWith(File.separator)) {

				temp = new File(path + tempList[i]);

			} else {

				temp = new File(path + File.separator + tempList[i]);

			}

			if (temp.isFile()) {

				temp.delete();

			}

			if (temp.isDirectory()) {

				// ��ɾ���ļ���������ļ�

				delAllFile(path + "/" + tempList[i]);

				// ��ɾ�����ļ���

				delFolder(path + "/" + tempList[i]);

				flag = true;

			}

		}

		return flag;

	}
	
	
	private static void createDirectory(String directory, String subDirectory) {



        String dir[];

        File fl = new File(directory);

        try {

            if (subDirectory == "" && fl.exists() != true) {

                fl.mkdir();

            } else if (subDirectory != "") {

                dir = subDirectory.replace('\\', '/').split("/");

                for (int i = 0; i < dir.length; i++) {

                    File subFile = new File(directory + File.separator + dir[i]);

                    if (subFile.exists() == false)

                        subFile.mkdir();

                    directory += File.separator + dir[i];

                }

            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }

    }
	/**

     * ѹ���ļ�

     * @param out org.apache.tools.zip.ZipOutputStream

     * @param file ��ѹ�����ļ�

     * @param base ѹ���ĸ�Ŀ¼

     */

    private static void zip(ZipOutputStream out, File file, String base) throws Exception {



        if (file.isDirectory()) {

            File[] fl = file.listFiles();

            base = base.length() == 0 ? "" : base + File.separator;

            for (int i = 0; i < fl.length; i++) {

                zip(out, fl[i], base + fl[i].getName());

            }

        } else {

            out.putNextEntry(new ZipEntry(base));

            //log.debug("���ѹ���ļ���" + base);

            FileInputStream in = new FileInputStream(file);

            int b;

            while ((b = in.read()) != -1) {

                out.write(b);

            }

            in.close();

        }

    }


    /**

     * ��ѹzip�ļ�

     * @param zipFileName ����ѹ��zip�ļ�·�������磺c:\\a.zip

     * @param outputDirectory ��ѹĿ���ļ���,���磺c:\\a\

     */

    public static void unZip(String zipFileName, String outputDirectory) throws Exception {



        ZipFile zipFile = new ZipFile(zipFileName);

        try {

            Enumeration<?> e = zipFile.entries();

            ZipEntry zipEntry = null;

            createDirectory(outputDirectory, "");

            while (e.hasMoreElements()) {

                zipEntry = (ZipEntry) e.nextElement();

                //log.debug("��ѹ��" + zipEntry.getName());

                if (zipEntry.isDirectory()) {

                    String name = zipEntry.getName();

                    name = name.substring(0, name.length() - 1);

                    File f = new File(outputDirectory + File.separator + name);

                    f.mkdir();

                    //log.debug("����Ŀ¼��" + outputDirectory + File.separator + name);

                } else {

                    String fileName = zipEntry.getName();

                    fileName = fileName.replace('\\', '/');

                    if (fileName.indexOf("/") != -1) {

                        createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));

                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());

                    }



                    File f = new File(outputDirectory + File.separator + zipEntry.getName());



                    f.createNewFile();

                    InputStream in = zipFile.getInputStream(zipEntry);

                    FileOutputStream out = new FileOutputStream(f);



                    byte[] by = new byte[1024];

                    int c;

                    while ((c = in.read(by)) != -1) {

                        out.write(by, 0, c);

                    }

                    in.close();

                    out.close();

                }

            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        } finally {

            zipFile.close();

            //log.debug("��ѹ��ɣ�");

        }



    }

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 File file = new File("E:\\hh");
		 //copyFileWithDirector("E:\\hh", "E:\\aa");
		
		// File file2 = new File("E:\\opt");
		// list(file);
		// copy(file,file2);
		/*Date myDate = new Date();
		DateFormat df = DateFormat.getDateInstance();
		System.out.println(df.format(myDate));*/
	}

}
