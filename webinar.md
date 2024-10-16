# JUnit 5's Best Features

- Functional interfaces
  - `Supplier<String>` for lazy error messages, [LazyErrorMessagesTest.java](src/test/java/com/oreilly/functionalinterfaces/LazyErrorMessageTest.java)
  - `Executable`
  - `ThrowingSupplier`, `ThrowingConsumer`
  - [ThrowableSuppliersTest.java](src/test/java/com/oreilly/functionalinterfaces/ThrowableSuppliersTest.java)
- Assertions
  - `assertAll`
  - `assertTimeout`, `assertTimeoutPreemptively`
  - `assertThrows`, `assertThrowsExactly`, `assertDoesNotThrow`
  - [AssertionsTests.java](src/test/java/com/oreilly/AssertionsTests.java)
- Display names
  - `@DisplayName`
  - `@DisplayNameGeneration`
  - [DisplayNameTests.java](src/test/java/com/oreilly/DisplayNameTests.java), [DisplayNameGeneratorDemo.java](src/test/java/com/oreilly/DisplayNameGeneratorDemo.java)
- Conditional test execution
  - `@EnabledOnOs`, `@EnabledOnJre`, `@EnabledOnJreRange` 
  - `@EnabledIfSystemProperty`, `@EnabledIfEnvironmentVariable`
  - `@EnabledIf`
  - All the same for disabling tests
  - [ConditionalTests.java](src/test/java/com/oreilly/ConditionalTests.java)
- Ordering
  - `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`
  - [OrderedTests.java](src/test/java/com/oreilly/OrderedTests.java)
- Parallel test execution
  - `@Execution(ExecutionMode.CONCURRENT)`
  - `@Execution(ExecutionMode.SAME_THREAD)`
  - Set values in `junit-platform.properties`
- Parameterized tests
  - `ParameterizedTest`
  - `@ValueSource`, `@EnumSource`, `@MethodSource`, `@CsvSource`, `@CsvFileSource`
  - [ParameterizedTests.java](src/test/java/com/oreilly/ParameterizedTests.java)
- Nested tests
  - `@Nested`
  - [NestedTests.java](src/test/java/com/oreilly/NestedTests.java), [TestingAStackDemo.java](src/test/java/com/oreilly/TestingAStackDemo.java)
- Tagging
  - `@Tag`
  - [TaggingDemoTest.java](src/test/java/com/oreilly/TaggingDemoTest.java)
- Assumptions
  - [AssumptionsTests.java](src/test/java/com/oreilly/AssumptionsTests.java)
- Lifecycle methods
  - `@BeforeAll`, `@AfterAll`, `@BeforeEach`, `@AfterEach`
  - `TestInstance.Lifecycle.PER_CLASS`, `TestInstance.Lifecycle.PER_METHOD`
  - [LifecycleTest.java](src/test/java/com/oreilly/LifecycleTest.java)
- Vintage engine