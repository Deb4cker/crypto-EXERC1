package Database;

import Domain.User;
import Security.PBKDF2;
import Security.SHA256;
import Security.AESCBC;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.*;
import java.security.Security;
import java.util.HashMap;

public class Db {

    private String path;
    private final String ivSaltPath = "saltIv.txt"; //"C:\\encriptedUsers\\saltIv.txt"

    public Db (){
        Security.addProvider(new BouncyCastleProvider());
        this.path = "users.txt"; //"C:\\encriptedUsers\\users.txt"
    }
    public Db (String path){
        this.path = path;
    }

    public HashMap<String, User> getAll(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            var response = new  HashMap<String, User>();
            var line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                response.put(fields[0], new User(fields[0], fields[1]));
                line = br.readLine();
            }
            return response;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public User getByUserName(String userName){
        return getAll().get(SHA256.encrypt(userName));
    }

    public void save (User user){

        var encryptedUser = SHA256.encrypt(user.getName());
        var saltIv = generateSaltIv(user.getPassword());
        var encryptedPass = AESCBC.encrypt(user.getPassword(), saltIv[0], saltIv[1].getBytes());

        try (var bw = new BufferedWriter(new FileWriter(path, true))){

            bw.write(encryptedUser + "," + encryptedPass);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var bw = new BufferedWriter(new FileWriter(ivSaltPath, true))){
            bw.write(saltIv[0] + "," + saltIv[1]);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] generateSaltIv(String password){
        var salt = SHA256.encrypt(password);
        var iv = PBKDF2.derivedKeyGen(salt, password, 1);
        return new String[]{salt, iv};
    }

    public String[] getSaltIvByPassword(String password){
        var pass = SHA256.encrypt(password);
        return getAllSaltIv().get(pass);
    }

    public HashMap<String, String[]> getAllSaltIv(){
        try (BufferedReader br = new BufferedReader(new FileReader(ivSaltPath))) {
            var response = new  HashMap<String, String[]>();
            var line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                response.put(fields[0], new String[]{fields[0], fields[1]});
                line = br.readLine();
            }
            return response;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
