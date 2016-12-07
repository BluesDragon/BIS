package com.test.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "account".
*/
public class AccountDao extends AbstractDao<Account, Long> {

    public static final String TABLENAME = "account";

    /**
     * Properties of entity Account.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(2, String.class, "sex", false, "SEX");
        public final static Property Tel = new Property(3, Integer.class, "tel", false, "TEL");
        public final static Property Address = new Property(4, String.class, "address", false, "ADDRESS");
        public final static Property Company = new Property(5, String.class, "company", false, "COMPANY");
        public final static Property Assets_type = new Property(6, Integer.class, "assets_type", false, "ASSETS_TYPE");
        public final static Property Consumer_grade = new Property(7, Integer.class, "consumer_grade", false, "CONSUMER_GRADE");
        public final static Property Card_id = new Property(8, String.class, "card_id", false, "CARD_ID");
        public final static Property Flag = new Property(9, String.class, "flag", false, "FLAG");
        public final static Property Attribution = new Property(10, String.class, "attribution", false, "ATTRIBUTION");
        public final static Property Integral = new Property(11, Integer.class, "integral", false, "INTEGRAL");
    }


    public AccountDao(DaoConfig config) {
        super(config);
    }
    
    public AccountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"account\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SEX\" TEXT," + // 2: sex
                "\"TEL\" INTEGER," + // 3: tel
                "\"ADDRESS\" TEXT," + // 4: address
                "\"COMPANY\" TEXT," + // 5: company
                "\"ASSETS_TYPE\" INTEGER," + // 6: assets_type
                "\"CONSUMER_GRADE\" INTEGER," + // 7: consumer_grade
                "\"CARD_ID\" TEXT," + // 8: card_id
                "\"FLAG\" TEXT," + // 9: flag
                "\"ATTRIBUTION\" TEXT," + // 10: attribution
                "\"INTEGRAL\" INTEGER);"); // 11: integral
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"account\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Account entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        Integer tel = entity.getTel();
        if (tel != null) {
            stmt.bindLong(4, tel);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(5, address);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(6, company);
        }
 
        Integer assets_type = entity.getAssets_type();
        if (assets_type != null) {
            stmt.bindLong(7, assets_type);
        }
 
        Integer consumer_grade = entity.getConsumer_grade();
        if (consumer_grade != null) {
            stmt.bindLong(8, consumer_grade);
        }
 
        String card_id = entity.getCard_id();
        if (card_id != null) {
            stmt.bindString(9, card_id);
        }
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(10, flag);
        }
 
        String attribution = entity.getAttribution();
        if (attribution != null) {
            stmt.bindString(11, attribution);
        }
 
        Integer integral = entity.getIntegral();
        if (integral != null) {
            stmt.bindLong(12, integral);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Account entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        Integer tel = entity.getTel();
        if (tel != null) {
            stmt.bindLong(4, tel);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(5, address);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(6, company);
        }
 
        Integer assets_type = entity.getAssets_type();
        if (assets_type != null) {
            stmt.bindLong(7, assets_type);
        }
 
        Integer consumer_grade = entity.getConsumer_grade();
        if (consumer_grade != null) {
            stmt.bindLong(8, consumer_grade);
        }
 
        String card_id = entity.getCard_id();
        if (card_id != null) {
            stmt.bindString(9, card_id);
        }
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(10, flag);
        }
 
        String attribution = entity.getAttribution();
        if (attribution != null) {
            stmt.bindString(11, attribution);
        }
 
        Integer integral = entity.getIntegral();
        if (integral != null) {
            stmt.bindLong(12, integral);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Account readEntity(Cursor cursor, int offset) {
        Account entity = new Account( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // tel
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // address
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // company
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // assets_type
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // consumer_grade
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // card_id
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // flag
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // attribution
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11) // integral
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Account entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTel(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCompany(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAssets_type(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setConsumer_grade(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setCard_id(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFlag(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAttribution(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIntegral(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Account entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Account entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Account entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}