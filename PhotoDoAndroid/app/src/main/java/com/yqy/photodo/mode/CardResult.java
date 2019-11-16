package com.yqy.photodo.mode;

import java.util.List;

/**
 * Created by yqy on 2019/11/12.
 */

public class CardResult {

    /**
     * request_id : 1573553963,b3aa288c-7e85-4f0f-9c7b-d731d2fd5a1d
     * time_used : 335
     * cards : [{"address":"江苏省扬州市邗江区汊河街道明星村汪圩组27号","birthday":"1997-11-08","gender":"男","id_card_number":"321027199711081211","name":"余秋雨","race":"汉","type":1,"side":"front"}]
     * image_id : IwJC7jtmax5O3Iat/f14hQ==
     */

    private String request_id;
    private int time_used;
    private String image_id;
    private List<CardsBean> cards;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * address : 江苏省扬州市邗江区汊河街道明星村汪圩组27号
         * birthday : 1997-11-08
         * gender : 男
         * id_card_number : 321027199711081211
         * name : 余秋雨
         * race : 汉
         * type : 1
         * side : front
         */

        private String address;
        private String birthday;
        private String gender;
        private String id_card_number;
        private String name;
        private String race;
        private int type;
        private String side;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }
    }
}
