package fr.mgargadennec;

import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.mgargadennec.tools.HelloWorld;
import fr.mgargadennec.tools.HelloWorldPrinter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ApplicationTests {
	private static ConfigurableApplicationContext ctx;

	@BeforeClass
	public static void beforeClass() {
		ctx = spy(SpringApplication.run(Application.class));
	}

	@Test
	public void testApplicationStart() {
		SpringBootApplication annotation = AnnotationUtils.getAnnotation(Application.class,
				SpringBootApplication.class);
		Assert.assertNotNull("Spring boot annotation (@SpringBootApplication) not used on Application class!",
				annotation);
		Assert.assertThat("Spring boot application not started !", ctx.getBeanDefinitionNames(),
				hasItemInArray(startsWith("org.springframework.boot")));
	}

	@Test
	public void should_contains_string_bean_named_hello() {
		Assert.assertTrue("Application context does not contains a bean name 'hello'", ctx.containsBean("hello"));
		Assert.assertTrue("'hello' is not of type string", ctx.getBean("hello") instanceof String);
	}

	@Test
	public void should_contains_a_second_bean_named_world() {
		Assert.assertTrue("Application context does not contains a bean name 'world'", ctx.containsBean("world"));
		Assert.assertTrue("'world' is not of type string", ctx.getBean("world") instanceof String);
	}

	@Test
	public void should_contains_a_third_bean_named_helloWorld() {
		Assert.assertTrue("Application context does not contains a bean name 'helloWorld'",
				ctx.containsBean("helloWorld"));
		Assert.assertTrue("'helloWorld' is not of type string", ctx.getBean("helloWorld") instanceof HelloWorld);

		HelloWorld bean = ctx.getBean("helloWorld", HelloWorld.class);

		Assert.assertTrue("'helloWorld' is not initialised correctly", bean.toString().equals("hello world"));
	}

	@Test
	public void should_contains_a_fourth_bean_renamed_fooBar() {
		Assert.assertTrue("Application context does not contains a bean name 'fooBar'", ctx.containsBean("fooBar"));

		Assert.assertTrue("'fooBar' is not of type HelloWorld", ctx.getBean("fooBar") instanceof HelloWorld);
		HelloWorld bean = ctx.getBean("fooBar", HelloWorld.class);

		Assert.assertTrue("'fooBar' is not of type string", bean.toString().equals("foo bar"));

		Assert.assertFalse("'fooBar' has not been renamed from something else",
				ctx.getBeanFactory().getBeanDefinition("fooBar").getFactoryMethodName().equals("fooBar"));
	}

	@Test
	public void should_contains_a_printer() throws InterruptedException {

		Assert.assertTrue("Application context does not contains a bean name 'printer'", ctx.containsBean("printer"));

		Assert.assertTrue("'printer' is not of type HelloWorldPrinter",
				ctx.getBean("printer") instanceof HelloWorldPrinter);

	}

	@Test
	public void should_test_scope() throws InterruptedException {
		Assert.assertTrue("Application context does not contains a bean name 'testScope'",
				ctx.containsBean("testScope"));

		Assert.assertTrue("'testScope' is not of type String", ctx.getBean("testScope") instanceof String);
		String bean = ctx.getBean("testScope", String.class);
		String bean2 = ctx.getBean("testScope", String.class);

		Assert.assertNotEquals("Beans should not be equals !", bean, bean2);

	}
	
	@Test
	public void should_test_scope_proxy() throws InterruptedException {
	}
}
