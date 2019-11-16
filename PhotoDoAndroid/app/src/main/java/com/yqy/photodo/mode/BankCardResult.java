package com.yqy.photodo.mode;

import java.util.List;

/**
 * Created by yqy on 2019/11/14.
 */

public class BankCardResult {

    /**
     * image_id : NTyDKpmLM7RklVcRyv2xPA==
     * request_id : 1524647092,eeee87f7-6c0f-4754-b108-afe8f42abe17
     * time_used : 427
     * bank_cards : [{"organization":["union"],"number":"6217000130008255555","bank":"建设银行","bound":{"left_bottom":{"y":354,"x":113},"right_top":{"y":90,"x":497},"right_bottom":{"y":337,"x":508},"left_top":{"y":106,"x":103}}}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<BankCardsBean> bank_cards;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

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

    public List<BankCardsBean> getBank_cards() {
        return bank_cards;
    }

    public void setBank_cards(List<BankCardsBean> bank_cards) {
        this.bank_cards = bank_cards;
    }

    public static class BankCardsBean {
        /**
         * organization : ["union"]
         * number : 6217000130008255555
         * bank : 建设银行
         * bound : {"left_bottom":{"y":354,"x":113},"right_top":{"y":90,"x":497},"right_bottom":{"y":337,"x":508},"left_top":{"y":106,"x":103}}
         */

        private String number;
        private String bank;
        private BoundBean bound;
        private List<String> organization;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public BoundBean getBound() {
            return bound;
        }

        public void setBound(BoundBean bound) {
            this.bound = bound;
        }

        public List<String> getOrganization() {
            return organization;
        }

        public void setOrganization(List<String> organization) {
            this.organization = organization;
        }

        public static class BoundBean {
            /**
             * left_bottom : {"y":354,"x":113}
             * right_top : {"y":90,"x":497}
             * right_bottom : {"y":337,"x":508}
             * left_top : {"y":106,"x":103}
             */

            private LeftBottomBean left_bottom;
            private RightTopBean right_top;
            private RightBottomBean right_bottom;
            private LeftTopBean left_top;

            public LeftBottomBean getLeft_bottom() {
                return left_bottom;
            }

            public void setLeft_bottom(LeftBottomBean left_bottom) {
                this.left_bottom = left_bottom;
            }

            public RightTopBean getRight_top() {
                return right_top;
            }

            public void setRight_top(RightTopBean right_top) {
                this.right_top = right_top;
            }

            public RightBottomBean getRight_bottom() {
                return right_bottom;
            }

            public void setRight_bottom(RightBottomBean right_bottom) {
                this.right_bottom = right_bottom;
            }

            public LeftTopBean getLeft_top() {
                return left_top;
            }

            public void setLeft_top(LeftTopBean left_top) {
                this.left_top = left_top;
            }

            public static class LeftBottomBean {
                /**
                 * y : 354
                 * x : 113
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RightTopBean {
                /**
                 * y : 90
                 * x : 497
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RightBottomBean {
                /**
                 * y : 337
                 * x : 508
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LeftTopBean {
                /**
                 * y : 106
                 * x : 103
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }
}
