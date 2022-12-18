package principal.domain.pet;

import java.util.Collection;
import principal.domain.user.User;
import principal.persistence.PetDAO;

public class PetService {
    private PetDAO dao;

    public PetService() {
        this.dao = new PetDAO();
    }

    public void createPet(String nickname, String race, User user) throws Exception {

        try {
            //Validamos
            if (nickname == null || nickname.trim().isEmpty()) {
                throw new Exception("Debe indicar el apodo");
            }

            if (race == null || race.trim().isEmpty()) {
                throw new Exception("Debe indicar la raza");
            }

            if (user == null) {
                throw new Exception("Debe indicar el Usuario");
            }

            //Creamos el mascota
            Pet pet = new Pet();
            pet.setNickname(nickname);
            pet.setRace(race);
            pet.setUser(user);

            dao.savePet(pet);

        } catch (Exception e) {
            throw e;
        }
    }

    public void updateNicknamePet(int id, String nickname, String race, int idUser) throws Exception {

        try {

            //Validamos
            if (id > 0) {
                throw new Exception("Debe indicar el id");
            }

            if (nickname == null || nickname.trim().isEmpty()) {
                throw new Exception("Debe indicar el apodo");
            }

            if (race == null || race.trim().isEmpty()) {
                throw new Exception("Debe indicar la raza");
            }

            if (idUser < 0) {
                throw new Exception("Debe indicar el Usuario");
            }

            //Buscamos
            Pet pet = findPetById(id);

            dao.updatePet(pet);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deletePet(int id) throws Exception {

        try {

            //Validamos 
            if (id < 0) {
                throw new Exception("Debe indicar el Id");
            }
            dao.deletePet(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public Pet findPetById(int id) throws Exception {

        try {

            //Validamos
            if (id < 0) {
                throw new Exception("Debe indicar el id");
            }
            Pet pet = dao.findPetById(id);
            //Verificamos
            if (pet == null) {
                throw new Exception("No se econtró mascota para el correo electrónico indicado");
            }

            return pet;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Pet> findAllPets() throws Exception {

        try {

            Collection<Pet> mascotas = dao.findAllPets();

            return mascotas;
        } catch (Exception e) {
            throw e;
        }
    }

    public void printAllPets() throws Exception {

        try {

            //Listamos los mascotas
            Collection<Pet> pets = findAllPets();

            //Imprimimos los mascotas
            if (pets.isEmpty()) {
                throw new Exception("No existen mascotas para imprimir");
            } else {
                for (Pet u : pets) {
                    System.out.println(u.toString());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
