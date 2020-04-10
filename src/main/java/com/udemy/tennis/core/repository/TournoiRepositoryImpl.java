package com.udemy.tennis.core.repository;


import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {


 public void create(Tournoi tournoi) {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     session.persist(tournoi);
 }
 public void delete(Long id) {
        Tournoi tournoi=getByID(id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
        System.out.println("Tournoi supprimé");

    }
 public Tournoi getByID(Long id) {
      Session  session=HibernateUtil.getSessionFactory().getCurrentSession();
      Tournoi tournoi = session.get(Tournoi.class, id);
        System.out.println("Tournoi affiché");
        return tournoi;
    }
 public List<Tournoi> tableTournoi() {
        Connection connection = null;
        List<Tournoi> tableTournoi=new ArrayList<>();
        try {
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            connection=dataSource.getConnection();
            PreparedStatement requeteRead = connection.prepareStatement("SELECT ID,NOM,PRENOM,CODE FROM TOURNOI");
            ResultSet retourRequeteRead=requeteRead.executeQuery();
            while(retourRequeteRead.next()){
                Tournoi tournoi=new Tournoi();
                tournoi.setNom(retourRequeteRead.getString("NOM"));
                tournoi.setCode(retourRequeteRead.getString("CODE"));
                tournoi.setId(retourRequeteRead.getLong("ID"));
                tableTournoi.add(tournoi);
            }
            System.out.println("tournois affichés");
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
        }return tableTournoi;
    }
}
