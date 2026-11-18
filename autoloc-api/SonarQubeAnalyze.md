# Impact Faible
* Remove the 'ApiResponses' wrapper from this annotation group [java:S1710]
* Rename this generic name to match the regular expression '^[A-Z][0-9]?$'. [java:S119]
* Rename this bean to match the regular expression '^[a-z][a-zA-Z0-9]*$'. [java:S6830]

# Impact Moyen
* Add a private constructor to hide the implicit public one. [java:S1118]
* Add at least one assertion to this test case. [java:S2699]
* Explicitly specify the time zone by passing a ZoneId or a Clock to the .now() method. [java:S8688]
* Return an empty collection instead of null. [java:S1168]
* Replace this use of System.out by a logger. [java:S106]
* Make this private field "final", it is never reassigned. [java:S9438]
* T is not used in the interface. [java:S2326]

# Impact Important
* Replace this persistent entity with a simple POJO or DTO object. [java:S4684]
* Add @Autowired or @Inject to one of the constructors. [java:S6829]



