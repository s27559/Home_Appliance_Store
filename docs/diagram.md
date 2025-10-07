# You can import this code into draw.io for final visual editing
aka when this is finished import into draw.io to fix things with visuals
also its missing some proper fomratting for uml so those need it to be fixed

Still missing:
- labled inharetance types
- reletion class
- qualititive relation

# -------
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

    Cart "1" <-- "1" CostumerAccount : has

    class Cart {
    
    }

    Cart "1" <-- "1" SellTransacation : made from contents

    class Transaction {
        <<Abstract>>
    }
    Transaction <|-- SellTransacation
    Transaction <|-- BuyTransaction
    Transaction <|-- MoveTransaction

    class SellTransacation {
    
    }

    SellTransacation "1" --> "0..*" Delivery : makes\n{if not pickup type}\n{XOR}
    BuyTransaction "1" --> "1..*" Delivery : makes\n{XOR}
    MoveTransaction "1" --> "1..*" Delivery : makes\n{XOR}

    class Delivery {
    
    }

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
        <<Singleton (only one instance)>>
    }

    Company <|-- CompanyAccount

    class Company {
    }

    Product "0..*" --> "1..*" Company : brand
    
    Account <|-- PersonAccount
    
    class PersonAccount {
        <<Abstract>>

        -surname : string
        -birthday : date
        /fullName : string
        /age : int
    }
    
    PersonAccount <|-- CostumerAccount
     
    class CostumerAccount {
    }

    class MoveTransaction {
    
    }

    BuyTransaction "0..*" --> "1..*" Company : supplier

    class BuyTransaction {
    
    }

    BuyTransaction "1..0" <-- "1" Request : creates\n{if approved}\n{XOR}
    MoveTransaction "1..0" <-- "1" Request : creates\n{if approved}\n{XOR}

    Request "0..*" --> "1" CompanyAccount : approved by

    class Request {
    
    }

    EmployeeAccount "1" <-- "0..*" Request : made by\n{if manager}

    class EmployeeAccount {
        -bonus : float
        -paidVacationDaysMax : int
        -unpaidVacationDaysMax : int
    }

    Role "1" *-- "0..*" EmployeeAccount : has
    PersonAccount <|-- EmployeeAccount

    class Role {
        -name : string
        -sallary : float
    }

    EmployeeAccount "1" o-- "0..*" Leave : for\n

    EmployeeAccount "1" o-- "0..*" CompanyBuilding : works at

    EmployeeAccount "1" <..> "1" Contract : for\n{history}
    CompanyBuilding "1" <..> "1" Contract : for\n{history}

    class Contract {
        -endDate : date
        -startDate : date
        /durationDays : int
        /employee : EmployeeAccount
        /store : Store
    }

    class Leave {
        -leaves : Leave [0..*]$
        -isSick : bool
        -isPaid : bool
        -startDate : date
        -endDate: date
        /durationDays : int
    }

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

    class Store {
    
    }
    
    StoragePlace <|-- Store

    class Wherehouse {
    
    }

    StoragePlace <|-- Wherehouse

    class ProductStored {
        
    }
    
    ProductStored "1" <..> "1" StoragePlace : ammount\n{bag}
    ProductStored "1" <..> "1" Product : ammount\n{bag}
    

    class StoragePlace {
    
    }

    CompanyBuilding <|-- StoragePlace

    class Office {
    
    }
    CompanyBuilding <|-- Office

    class CompanyBuilding {
    
    }

    Product "0..*" <-- "0..1" StoragePlace : stored at
    Street "1" *-- "0..*" CompanyBuilding : at
```
