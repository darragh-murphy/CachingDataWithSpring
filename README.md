# Caching Data With Spring
Spring 3.1 makes it incredibly easy to introduce caching strategies that can have a dramatic effect on the performance of your application.

When ever you have a method that will always return the same value giving the same inputs this is a candidate for caching.

In order to test caching data with Spring I’ve create a simple Spring Boot application which has two Data Access Objects classes. Both are identical except that one has Spring cache enabled using annotations.
