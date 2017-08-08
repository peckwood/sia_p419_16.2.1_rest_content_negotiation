Followed http://www.baeldung.com/spring-mvc-content-negotiation-json-xml and test with 

- http://localhost:8080/sia_p419_16.2.1_rest_content_negotiation/users.json (json)
- http://localhost:8080/sia_p419_16.2.1_rest_content_negotiation/users.xml (XML)
- http://localhost:8080/sia_p419_16.2.1_rest_content_negotiation/users (json(default))

Note this doesn't work with Spring 4.0.7, only 4.3.4 or later.

The content below was based on the author's book but I didn't fully understand so you can ignore.

---

How this works?

1. user visit the URL http://localhost:8080/sia_p419_16.2.1_rest_content_negotiation/users
2. the logical view name
   1. when a controller’s handler method finishes, a logical view name is usually returned. If the method doesn’t directly return a logical view name (if the method returns void, for example), the logical view name is derived from the request’s URL
   2. `rest.controller.UserController.users(long, int)` didn't directly return a logical view name
   3. The logic view name is `users`
3. the configured BeanNameViewResolver resolves the View declared in the spittles() method

---

Maybe it is like this, but this is how I thought:

1. user visit the URL http://localhost:8080/sia_p419_16.2.1_rest_content_negotiation/users
2. Content negociation
   1. determine request media types
      1. URL extension, nothing
      2. Accept header
         1. browser's Accept header is`Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8`
         2. Considered no help either
      3. fall back to default media type(default default is /), as we configured in bean `ContentNegotiationConfigurer`, default media type is HTML
   2. Finds the best view for the request media type JSON
      1. `ContentNegotiatingViewResolver` delegates `BeanNameViewResolver` to resolve the view
      2. `BeanNameViewResolver` finds out the logical view name
         1. when a controller’s handler method finishes, a logical view name is usually returned. If the method doesn’t directly return a logical view name (if the method returns void, for example), the logical view name is derived from the request’s URL
         2. `rest.controller.UserController.users(long, int)` didn't directly return a logical view name
         3. The logic view name is `users`
      3. As `BeanNameViewResolver` resolves logical view name to bean.It resolves the View bean of name "users"


---

> https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc
>
> https://spring.io/blog/2013/06/03/content-negotiation-using-views/