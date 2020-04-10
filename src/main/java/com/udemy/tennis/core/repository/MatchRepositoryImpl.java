package com.udemy.tennis.core.repository;


import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.MatchDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Match;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {

 public void create(Match match) {
     Connection connection = null;
     try {
         DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

        connection=dataSource.getConnection();
        PreparedStatement requeteCreate = connection.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        requeteCreate.setLong(1,match.getEpreuve().getId());
        requeteCreate.setLong(2,match.getVainqueur().getId());
        requeteCreate.setLong(3,match.getFinaliste().getId());
        requeteCreate.executeUpdate();
        ResultSet retourGenerateKeys=requeteCreate.getGeneratedKeys();
        if(retourGenerateKeys.next()){
           match.setId(retourGenerateKeys.getLong(1));
        }
         System.out.println("match crée");
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
        }
    }
 public Match getByID(Long id) {
        Match match=null;
        Session session=null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        match = session.get(Match.class, id);
        System.out.println("Match affiché");
        return match;
    }

}
