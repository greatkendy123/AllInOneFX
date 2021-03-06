package com.allinonefx.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.Date;

public class Language extends RecursiveTreeObject<Language> {

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column language.language_id
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    private Byte language_id;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column language.name
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column language.last_update
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    private Date last_update;

    /**
     * This method was generated by MyBatis Generator. This method returns the
     * value of the database column language.language_id
     *
     * @return the value of language.language_id
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public Byte getLanguage_id() {
        return language_id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the
     * value of the database column language.language_id
     *
     * @param language_id the value for language.language_id
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public void setLanguage_id(Byte language_id) {
        this.language_id = language_id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the
     * value of the database column language.name
     *
     * @return the value of language.name
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the
     * value of the database column language.name
     *
     * @param name the value for language.name
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the
     * value of the database column language.last_update
     *
     * @return the value of language.last_update
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public Date getLast_update() {
        return last_update;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the
     * value of the database column language.last_update
     *
     * @param last_update the value for language.last_update
     *
     * @mbg.generated Sat Jan 27 19:28:17 CET 2018
     */
    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
