package anomalie;

public class CategorieAnomalie {
    private int idCategorieAnomalie;
    private String categorie;

    public CategorieAnomalie(){}
    public CategorieAnomalie(int idCategorieAnomalie, String categorie) throws Exception {
        setIdCategorieAnomalie(idCategorieAnomalie);
        setCategorie(categorie);
    }

    public int getIdCategorieAnomalie(){
        return this.idCategorieAnomalie;
    }
    public void setIdCategorieAnomalie(int idCategorieAnomalie) throws Exception {
        if (idCategorieAnomalie <= 0) throw new Exception("L'ID de categorieAnomalie doit être positive");
        this.idCategorieAnomalie = idCategorieAnomalie;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}