# You can import this code into draw.io for final visual editing
## aka when this is finished import into draw.io to fix things with visuals

``` mermaid
---
Title: Home Applience Store
---
classDiagram
    class Country {
        -name : string
    }

    Country "1" *-- "0..*" City : in

    class City {
        -name : string
        /country : Country
    }

    City "1" *-- "0..*" Street : in

    class Street {
        -name : string
        /city : City
    }
    Street "1" *-- "0..*" Account : at

    class Account {
        <<Abstract>>
        -accounts : Account [0..*]$ 
        -id : int
        -name : string
        -phoneNumber : int
        -email : string
        -streetNumber : int
        /street : Street
        /fullAdress : string
        /fullPhoneNumber : string
    } 

    Account <|-- CompanyAccount

    class CompanyAccount {
    }
    
    Account <|-- PersonAccount
    
    class PersonAccount {
        <<Abstract>>

        -surname : string
        -birthday : date
        /fullName : string
        /age : int
    }

    class CostumerAccount {
    }

    class EmployeeAccount {
    }    

    PersonAccount <|-- CostumerAccount
    PersonAccount <|-- EmployeeAccount

    class Product {
        -products : Product [0..*]$
        -id : int
        -name : string
        -modelNumber : string
        -price : float
        -weight : float
        /category : Category
        /properties : PropertyValue [0..*]

        +search(name : string) : Product [0..*]$
        +filterCategory(products : Product [0..*], category : Category) : Product [0..*]$
        +filterPrice(products : Product [0..*], priceBottom : float = 0.0, priceTop : float = -1.0) : Product [0..*]$
        +filterWeight(products : Product [0..*], weightBottom : float = 0.0, weightTop : float = -1.0) : Product [0..*]$
        +filterProperties(products : Product [0..*], propertiesBottom : PropertyValue [0..*], propertiesTop : PropertyValue [0..*]) : Product [0..*]$
    }

    note for Product "negative value for filter means infinity"

    PropertyValue "0..*" o-- "0..1" Product : properties of product\n{ property types must be in \nthe category of the product }
    Category "1" *-- "0..*" Product : in

    class Category {
        -categories : Category [0..*]$
        -name : string
        /parentCategory : Category [0..1]
        /suncategories : Category [0..*]
        /properties : PropertyType [0..*]
    }

    Category "0..1" <-- "0..*" Category : parent category
    Category "0..*" <-- "0..*" Category : suncategories
    Category "0..*" o-- "0..*" PropertyType : of

    class PropertyType {
        -name : string
        -metric : string
    }

    PropertyType "1" *-- "0..*" PropertyValue : of type

    class PropertyValue {
        /propertyType : PropertyType
        -value : float
    }


    ```
