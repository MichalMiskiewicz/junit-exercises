# README - CalculatorTest class
The CalculatorTest class is a JUnit test class for the Calculator class. 

### Fields 
- `private Calculator calculator`: An instance of the `Calculator` class is created in the `setUp()` 
method and used in all test cases.
### Methods 
  - `static Stream<Arguments> getTwoArguments()`
    - This method returns a stream of three pairs of arguments that are used 
    in the `shouldThrowAnExceptionOnInvalidArgument()` parameterized test. 
    Each argument is a string representing either a valid number or an invalid one.


- `@BeforeEach void setUp()`
  - This method creates an instance of the `Calculator` class before each test case.


- `@Test void shouldAddTwoCorrectNumbers()`
  - This test case verifies that the `add()` method of the `Calculator` class 
  can add two numbers correctly. It initializes two strings that represent two numbers, 
  calls the `add()` method with these two numbers as arguments, 
  and then checks that the result is equal to the expected value.


- `@ParameterizedTest void shouldThrowAnExceptionOnInvalidArgument(String a, String b)`
  - This test case uses the `@ParameterizedTest` annotation to run the same test with different inputs. 
  The `getTwoArguments()` method provides three pairs of invalid arguments that are passed 
  to this test case. This test case verifies that the add() method throws 
  a `NumberFormatException` when it receives an invalid argument.


- `@Test void shouldThrowExceptionOnIntegerOverflow()`
  - This test case verifies that the `add()` method throws an `ArithmeticException` when the result 
  of adding two integers overflows the `Integer` data type. 
  It initializes two strings that represent two numbers, one of which is `Integer.MAX_VALUE`, 
  calls the `add()` method with these two numbers as arguments, 
  and then checks that an `ArithmeticException` with the expected message is thrown.