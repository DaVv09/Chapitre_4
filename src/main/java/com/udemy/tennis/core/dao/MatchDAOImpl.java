package com.udemy.tennis.core.dao;

import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.entity.Match;
import com.udemy.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDAOImpl {
    public void createMatchWithScore(Match match){
            Connection connection = null;
            try {
                DataSource dataSource= DataSourceProvider.getSingleDataSourceInstance();

                connection=dataSource.getConnection();
                connection.setAutoCommit(false);
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


                PreparedStatement requeteCreate2 = connection.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                requeteCreate2.setLong(1,match.getScore().getMatch().getId());
                requeteCreate2.setByte(2,match.getScore().getSet1());
                requeteCreate2.setByte(3,match.getScore().getSet2());
                requeteCreate2.setByte(4,match.getScore().getSet3());
                if(match.getScore().getSet4()==null){
                    requeteCreate2.setNull(5,Types.TINYINT);
                }else{
                    requeteCreate2.setByte(5,match.getScore().getSet4());}
                if(match.getScore().getSet5()==null) {
                    requeteCreate2.setNull(6, Types.TINYINT);
                }else{
                    requeteCreate2.setByte(6,match.getScore().getSet5());}

                requeteCreate2.executeUpdate();
                ResultSet retourGenerateKeys2=requeteCreate2.getGeneratedKeys();
                if(retourGenerateKeys.next()){
                    match.getScore().setId(retourGenerateKeys.getLong(1));
                }
                System.out.println("score crée");
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (connection != null) connection.rollback(); {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}
