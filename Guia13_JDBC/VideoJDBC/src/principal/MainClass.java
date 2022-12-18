package principal;

import principal.domain.user.UserService;
import java.util.logging.Logger;
import java.util.logging.Level;
import principal.domain.pet.PetService;
import principal.domain.user.User;


public class MainClass {

    public static void main(String[] args) {

        UserService userService = new UserService();
        PetService petService = new PetService();

        /*try {
            userService.createUser("chiquito@chiquito", "12345678");
        } catch(Exception ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        try {
            //Creamos  usuarios
            userService.createUser("fiorde@hotmail.com", "fiorde14");
            userService.createUser("tincho@hotmail.com", "eggprogramacion");
            userService.printUsers();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        
        
        try {
            //Modificamos un usuario
            userService.updatePasswordUser("fiorde@hotmail.com", "fiorde14", "fiorde15");
            userService.printUsers();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        
        
        try {
            //Eliminamos un usuario
            userService.deleteUser("fiorde@hotmail.com");
            userService.printUsers();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        
        
        try {
            //Buscamos el Usuario por correo
            User user = userService.findUserByEmail("tincho@hotmail.com");
            //Creamos Mascota con el Usuario anterior
            petService.createPet("Chiquito", "Beagle", user);

            //Mostramos Mascota con su Usuario
            petService.printAllPets();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        
        
    }
    

}
