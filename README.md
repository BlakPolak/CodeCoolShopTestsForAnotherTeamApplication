#UNICORNS REFACTORING AND TESTS

**1.Tests**

* DAO:
    * BaseDAoTest
        * 100% methods coverage
        * all tests passed
    * BasketDaoSqliteTest
        * 100% methods coverage
        * 3/4 tests failed
    * OrderDaoSqliteTest
        * 71% methods coverage
        * all tests passed
    * ProductCategoryDaoSqliteTest
        * 100% methods coverage
        * 1/2 tests failed
    * SupplierDaoSqliteTest
        * 100% methods coverage
        * 1/2 tests failed
    * UserDaoSqliteTest
        * 100% methods coverage
        * all tests passed
    * SqliteJDBCConnector
        * 100% methods coverage
        * 2/4 tests failed
    * ProductDaoSqliteTest
         * 100% methods coverage
         * 4/9 tests failed

* MODEL:
    * BaseModelTest
        * 90% methods coverage
        * 1/5 tests failed
    * BasketTest
        * 87% methods coverage
        * all tests passed
    * BasketItemTest
        * 100% methods coverage
        * all test passed
    * OrderTest
        * 84% methods coverage
        * all test passed
    * ProductTest
        * 90% methods coverage
        * 2/9 test failed
    * ProductCategoryTest
        * 57% methods coverage
        * all tests passed
    * SendEmailTest
    * SupplierTest
        * 40% methods coverage
        * all tests passed
    * UserTest
        * 33% methods coverage
        * 2/4 test failed
        
* CONTROLLER:
    * BasketController
        * renderBasket() method
    * ConfirmController
        * displayConfirmForm() method
    * OrderController
        * showAll() method
    * PaymentController
        * displayPaymentForm()

**2. Refactoring**

- dependencies injection
    * add new class constructors with connection as dependency injection
- methods validations:
    * check for border values(null, below zero arguments)
- remove unused methods
- refactor methods responsible for web application:
    * replaced old-fashioned paths in Application class with up-to-date approach
    * controllers methods refactor so that they return ModelAndView


**3. Comments**

- mostly clean code 
- it's easy to understand
- contains negligible weaknesses






