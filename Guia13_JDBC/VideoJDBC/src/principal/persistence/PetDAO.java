package principal.persistence;

import java.util.ArrayList;
import java.util.Collection;
import principal.domain.user.User;
import principal.domain.pet.Pet;
import principal.domain.user.UserService;

public final class PetDAO extends DAO {

    private final UserService userService;

    public PetDAO() {
        this.userService = new UserService();
    }

    public void savePet(Pet pet) throws Exception {
        try {
            if (pet == null) {
                throw new Exception("Debe indicar el mascota");
            }
            String sql = "INSERT INTO pet (nickname, race, id_user) "
                    + "VALUES ( '" + pet.getNickname() + "' , '" + pet.getRace() + "' ," + pet.getUser().getId() + " );";

            System.out.println(sql);
            insertUpdateDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            dataBaseDisconnect();
        }
    }

    public void updatePet(Pet pet) throws Exception {
        try {
            if (pet == null) {
                throw new Exception("Debe indicar el mascota que desea modificar");
            }
            String sql = "UPDATE pet SET "
                    + " nickname = '" + pet.getNickname() + "' , race = '" + pet.getRace() + "' , i = " + pet.getUser().getId()
                    + " WHERE id = '" + pet.getId() + "'";
            insertUpdateDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            dataBaseDisconnect();
        }
    }

    public void deletePet(int id) throws Exception {
        try {
            String sql = "DELETE FROM pet WHERE id = " + id + "";
            insertUpdateDelete(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            dataBaseDisconnect();
        }
    }

    public Pet findPetById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM pet WHERE id = " + id + "";
            selectDataBase(sql);
            Pet pet = null;
            while (result.next()) {
                pet = new Pet();
                pet.setId(result.getInt(1));
                pet.setNickname(result.getString(2));
                pet.setRace(result.getString(3));
                Integer idUsuario = result.getInt(4);
                User user = userService.findUserById(idUsuario);
                pet.setUser(user);
            }
            dataBaseDisconnect();
            return pet;
        } catch (Exception e) {
            dataBaseConnection();
            throw e;
        }
    }

    public Collection<Pet> findAllPets() throws Exception {
        try {
            String sql = "SELECT * FROM pet ";
            selectDataBase(sql);
            Pet pet = null;
            Collection<Pet> pets = new ArrayList();
            while (result.next()) {
                pet = new Pet();
                pet.setId(result.getInt(1));
                pet.setNickname(result.getString(2));
                pet.setRace(result.getString(3));
                Integer idUsuario = result.getInt(4);
                User user = userService.findUserById(idUsuario);
                pet.setUser(user);
                pets.add(pet);
            }
            dataBaseDisconnect();
            return pets;
        } catch (Exception e) {
            e.printStackTrace();
            dataBaseDisconnect();
            throw e;
        }
    }
}