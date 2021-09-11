package templates;

import definitions.annotations.*;
import definitions.types.*;

import java.util.Date;

/**
 * API Template
 */
@SuppressWarnings("unused")
class TemplateV1 {
    public static class Enums {

        /**
         * User status
         */
        enum UserStatus implements FixedEnumValueInterface {
            INIT(0),
            DISABLED(2),
            IN_CLASS(4),
            ;
            int value;

            UserStatus(int value) {
                this.value = value;
            }

            @Override
            public int getValue() {
                return value;
            }
        }

        @Memo({
            "Payment methods",
            "this comment was added by `memo`"
        })
        @StringEnum
        @AsBO
        enum PaymentMethod {
            @Memo("wechat pay")
            WECHAT,
            @Memo
            ALIPAY,
        }

        /**
         * Gender
         *
         * This comment was added by javadoc
         */
        enum Gender {
            /**
             * Unknown
             */
            UNKNOWN,
            // one line doc
            MALE,
            /** inline doc */
            FEMALE,
        }
    }


    public class Models {
        /**
         * User info
         * second line
         */
        @RESTFul
        class User extends UserProfile {
            /**
             * a description for testing
             */
            @DBField("id")
            @Mutable
            Integer id;
            /**
             * another description for testing
             */
            @DBField
            String name;
            String avatar;
        }

        /**
         * User profile
         */
        class UserProfile {
            @DBField
            String name;
            // @DBField
            // Integer age;
        }

        /**
         * Share Payload
         */
        public class SharePayload {
            /**
             * shared title
             */
            String title;
            /**
             * shared description
             */
            String description;
            /**
             * shared icon
             */
            String icon;
            /**
             * link
             */
            String url;
        }

        class Article {
            @Optional
            @Mutable
            Integer id;
            String title;
            @Optional
            String content;
            User user;
            @Optional
            Integer commentsCount;
            /**
             * isFavorite
             */
            @Optional
            Boolean favorite;
            /**
             * isHot
             */
            @Optional
            Boolean hot;
            Date createdAt;
        }

        class ArticleComment {
            Integer id;
            User user;
            String content;
            Date createdAt;
        }
    }

    class AdminControllers {
        class Article {
            /**
             * get articles list
             */
            class GetArticles {
                @Response
                Models.Article[] articles;
            }
        }

    }

    class Controllers {
        class Article {
            /**
             * get articles list
             */
            class GetArticles {
                @Response
                Models.Article[] articles;
            }

            /**
             * get one article
             */
            class GetArticle {
                @Request
                Integer id;
                @Response
                Models.Article article;
            }

            /**
             * add article
             */
            @Transactional
            class CreateArticle {
                @Request
                String title;
                @Request
                String content;

                @Response
                Models.Article article;
            }

            /**
             * get article comments
             */
            class GetArticleComments {
                @Request
                Integer articleId;
                @Request
                Integer page;

                @Response
                Models.ArticleComment[] comments;
            }
        }

        /**
         * User Controller
         *
         * description
         */
        public class User {
            @Methods({MethodType.POST, MethodType.DELETE})
            @Middleware("admin")
            class GetUsers {
                /**
                 * ID
                 */
                @Request
                Integer id;

                @Request
                Enums.Gender gender;

                @Request
                @Optional
                Integer page;

                @Request
                @Optional
                Models.User testUser;

                @Request
                @Optional
                Models.User[] testUsers;

                @Response
                String val;

                @Response
                Models.User user;
            }

            /**
             * Create new user
             * description
             */
            @Methods({MethodType.POST})
            class CreateUser {
                /**
                 * ID
                 */
                @Request
                @LaravelValidateRule({"xxxxx"})
                String email;
                @Request
                String[] emails;
                @Request
                Enums.Gender gender;
                @Request
                Enums.Gender[] genders;
                @Request
                Integer id;
                @Request
                Integer[] ids;

                @Request
                DateYm ym;
                @Request
                DateYm[] ymarray;
                @Request
                DateYm[][][][] ymarrayn;

                @Request
                File file;

                @Response
                Models.User user;
                @Response
                Models.User[] users;
            }
        }
    }

}
