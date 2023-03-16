package apk.karmak.retrofitapp;

public class PatientModal {
    Integer id;
    String firstname;
    String lastname;
    String middlename;
    String bith;
    String pol;
    String image;
    Integer user_id;
    String created_at;
    String updated_at;


    public PatientModal(Integer id,
                        String firstname,
                        String lastname,
                        String middlename,
                        String bith,
                        String pol,
                        String image,
                        Integer user_id,
                        String created_at,
                        String updated_at) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.bith = bith;
        this.pol = pol;
        this.image = image;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
