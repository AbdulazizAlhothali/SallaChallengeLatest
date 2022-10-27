## Salla Coding Challenge

You will be given 2 images for the design, 2 endpoints, 2 font files, and a JSON file, the first is to get brand
details and the second is to get product details. Your challenge is to implement brand details and product
details in the design.

Rules
- you will need to use the MVVM design pattern to implement this functionality.
- Use jetpack navigation to navigate between fragments
- Don’t use any library to implement images slider in the product details
- Don’t use XML file to add background on views
- Use any dependency injection library you like it
- Use data binding & binding view
- Add custom font for all text in the project {choose the font file depending on the JSON file}
- Don’t use card view component
- Take views background color from the JSON file
Before you start, you will need to create a public repository (Github) and then send me an email so I can

confirm when the repository was created.
Once completed please push everything to the public Github repository and then I can check your code.

APIs
GET
https://salla.sa/api/v1/brands/259940351?page=1&per_page=

in the response, if cursor.next == null it’s mean this page is last one

GET
https://salla.sa/api/v1/products/{product_id}/details

Headers

Currency: SAR

AppVersion: 3.0.

Store-Identifier: {take id value from the JSON file}
