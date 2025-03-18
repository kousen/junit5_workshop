# JUnit 5 Workshop Exercises

## Exercise 1: Basic Assertions and Test Lifecycle
1. Create a new class called `Calculator` with methods for addition, subtraction, multiplication, and division
2. Write tests using different assertion methods:
   - Use `assertEquals` for expected values
   - Use `assertThrows` to verify division by zero throws an exception
   - Use `assertAll` to group multiple assertions together
3. Implement `@BeforeEach` and `@AfterEach` methods to set up and tear down test resources

## Exercise 2: Parameterized Tests
1. Create a `StringUtils` class with a method `isPalindrome(String)` that checks if a string is a palindrome
2. Write a parameterized test using `@ParameterizedTest` with:
   - `@ValueSource` for simple palindromes like "radar", "level"
   - `@CsvSource` for pairs of strings and expected boolean results
   - `@MethodSource` for generating more complex test cases

## Exercise 3: Nested and Dynamic Tests
1. Create a `BankAccount` class with methods for `deposit`, `withdraw`, and `getBalance`
2. Write a nested test class using `@Nested` for different account states:
   - "When new" with tests for a newly created account
   - "After deposits" with tests for accounts with deposits
   - "After withdrawals" with tests for withdrawals
3. Create a dynamic test factory using `@TestFactory` that generates tests for different account scenarios

## Exercise 4: Conditional Tests and Tagging
1. Update your `BankAccount` class to include a `transferFunds` method
2. Write tests with conditional execution:
   - Use `@EnabledOnOs` to run platform-specific tests
   - Use `@EnabledIfSystemProperty` to conditionally run tests
3. Add tags to your tests using `@Tag`:
   - Tag tests as "fast" or "slow"
   - Tag tests as "unit" or "integration"
4. Run only tests with specific tags using the Gradle command

## Exercise 5: AssertJ Fluent Assertions
1. Create a `User` class with fields for name, email, and roles (list of strings)
2. Write tests using AssertJ's fluent assertions:
   - Chain multiple assertions for a single object
   - Test collections using `contains`, `hasSize`, etc.
   - Use extraction to test specific properties
   - Use soft assertions to collect multiple failures

## Exercise 6: Extensions and Custom Annotations
1. Create a custom extension that logs test execution time
   - Implement `BeforeTestExecutionCallback` and `AfterTestExecutionCallback`
2. Create a custom annotation `@SlowTest` that combines `@Test` and `@Tag("slow")`
3. Apply your extension using:
   - Direct registration with `@ExtendWith`
   - Automatic discovery via service loader
4. Use your custom annotation on appropriate tests

## Exercise 7: Assumptions and Test Reporting
1. Create a test class with assumptions:
   - Use `assumeTrue` to conditionally run tests
   - Use `assumingThat` for conditional assertions
2. Inject and use `TestReporter` to report custom values
3. Run tests and examine the test report

## Exercise 8: Property-Based Testing with jqwik
1. Create a `SortingAlgorithm` class with a method to sort a list of integers
2. Write property tests using jqwik's `@Property` annotation:
   - Verify that sorting preserves all elements
   - Test that the result is actually sorted
   - Test that empty lists remain empty when sorted
3. Use custom generators with `@Provide` to create test data

## Bonus Exercise: Migrating from JUnit 4
1. Take an existing JUnit 4 test class (or create one)
2. Migrate it to JUnit 5:
   - Update annotations (`@Before` to `@BeforeEach`, etc.)
   - Replace `@RunWith` with `@ExtendWith`
   - Update assertion methods to use JUnit 5 style
3. Run both the old and new tests using the JUnit Vintage engine

## Challenge: Test-Driven Development
1. Use TDD to implement a `StringCalculator` class:
   - Method `add(String numbers)` takes a string of comma-separated numbers
   - Returns 0 for empty string
   - Returns sum of numbers for "1,2,3"
   - Handles newlines as separators ("1\n2,3")
   - Supports custom delimiters ("//;\n1;2")
   - Throws exceptions for negative numbers with informative message
2. Write tests first, then implement features one by one