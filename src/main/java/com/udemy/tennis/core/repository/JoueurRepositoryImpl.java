package com.udemy.tennis.core.repository;


import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {


 public void create(Joueur joueur) {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     session.persist(joueur);
 }
 public void delete(Long id) {
        Joueur joueur=getByID(id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé");

    }
 public Joueur getByID(Long id) {
        Joueur joueur=null;
        Session session=null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);
        System.out.println("Joueur affiché");
        return joueur;
    }
 public List<Joueur> tableJoueur() {
        Connection connection = null;
        List<Joueur> tableJoueur=new ArrayList<>();
        try {
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            connection=dataSource.getConnection();
            PreparedStatement requeteRead = connection.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR");
            ResultSet retourRequeteRead=requeteRead.executeQuery();
            while(retourRequeteRead.next()){
                Joueur joueur=new Joueur();
                joueur.setNom(retourRequeteRead.getString("NOM"));
                joueur.setPrenom(retourRequeteRead.getString("PRENOM"));
                joueur.setSexe(retourRequeteRead.getString("SEXE").charAt(0));
                joueur.setId(retourRequeteRead.getLong("ID"));
                tableJoueur.add(joueur);
            }
            System.out.println("Joueur affiché");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return tableJoueur;
    }
}
