#!/bin/bash

TABLE_NAME="r5tm1-CarRentAppVolume-dev2"
AWS_PROFILE="t1-dev2"
REGION="eu-west-1"

#Users

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#0264f885-b5af-4633-8742-24dd8c35c12d"},
    "USER#FIRST_NAME": {"S": "Abigail"},
    "USER#LAST_NAME": {"S": "Anderson"},
    "USER#USERNAME": {"S": "Abigail Anderson"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "USER#FIRST_NAME": {"S": "Benjamin"},
    "USER#LAST_NAME": {"S": "Brown"},
    "USER#USERNAME": {"S": "Benjamin Brown"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#66b313cb-2941-4b1b-9a35-dc6bbcb1fcb9"},
    "USER#FIRST_NAME": {"S": "Charlotte"},
    "USER#LAST_NAME": {"S": "Carter"},
    "USER#USERNAME": {"S": "Charlotte Carter"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#cb8799fd-a94a-46a1-90ce-ba4533ffed9f"},
    "USER#FIRST_NAME": {"S": "Daniel"},
    "USER#LAST_NAME": {"S": "Davis"},
    "USER#USERNAME": {"S": "Daniel Davis"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#f6a07d92-3ea4-4a11-94f1-5982cfd23650"},
    "USER#FIRST_NAME": {"S": "Emily"},
    "USER#LAST_NAME": {"S": "Edwards"},
    "USER#USERNAME": {"S": "Emily Edwards"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#76c1690c-b960-43ef-bc0b-fc712c74a845"},
    "USER#FIRST_NAME": {"S": "Felix"},
    "USER#LAST_NAME": {"S": "Foster"},
    "USER#USERNAME": {"S": "Felix Foster"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#80bb1573-5d62-419a-806c-4f55b8936414"},
    "USER#FIRST_NAME": {"S": "Grace"},
    "USER#LAST_NAME": {"S": "Garcia"},
    "USER#USERNAME": {"S": "Grace Garcia"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#ffa90dc8-760a-46d7-bec2-6019ef9b8450"},
    "USER#FIRST_NAME": {"S": "Henry"},
    "USER#LAST_NAME": {"S": "Harris"},
    "USER#USERNAME": {"S": "Henry Harris"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#a965798d-ec20-48dd-a0f3-03e60a24b85d"},
    "USER#FIRST_NAME": {"S": "Isabella"},
    "USER#LAST_NAME": {"S": "Ingram"},
    "USER#USERNAME": {"S": "Isabella Ingram"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#fe922b05-b460-4ea9-8780-aca68d337916"},
    "USER#FIRST_NAME": {"S": "Jack"},
    "USER#LAST_NAME": {"S": "Johnson"},
    "USER#USERNAME": {"S": "Jack Johnson"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#4b9a9ea1-32aa-4cde-929c-3748af4302df"},
    "USER#FIRST_NAME": {"S": "Katherine"},
    "USER#LAST_NAME": {"S": "King"},
    "USER#USERNAME": {"S": "Katherine King"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#2ac8c915-7c40-4a1e-b072-19ba15825f5b"},
    "USER#FIRST_NAME": {"S": "Liam"},
    "USER#LAST_NAME": {"S": "Lewis"},
    "USER#USERNAME": {"S": "Liam Lewis"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#4733dc70-6c88-4f05-897c-b4c0820b10cc"},
    "USER#FIRST_NAME": {"S": "Mia"},
    "USER#LAST_NAME": {"S": "Miller"},
    "USER#USERNAME": {"S": "Mia Miller"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#e593163a-96f5-48d2-b35f-63ba283d1e6c"},
    "USER#FIRST_NAME": {"S": "Nathan"},
    "USER#LAST_NAME": {"S": "Nelson"},
    "USER#USERNAME": {"S": "Nathan Nelson"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#df5b0680-1603-4ca3-8571-5a0e4fe4c0d6"},
    "USER#FIRST_NAME": {"S": "Olivia"},
    "USER#LAST_NAME": {"S": "Owens"},
    "USER#USERNAME": {"S": "Olivia Owens"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#49e4daf7-a43c-4d2a-a260-41a6d4a82a59"},
    "USER#FIRST_NAME": {"S": "Peter"},
    "USER#LAST_NAME": {"S": "Parker"},
    "USER#USERNAME": {"S": "Peter Parker"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#fabbaf9e-5473-4965-9d40-9b3b59862e29"},
    "USER#FIRST_NAME": {"S": "Elizabeth"},
    "USER#LAST_NAME": {"S": "Quinn"},
    "USER#USERNAME": {"S": "Elizabeth Quinn"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#d2162054-3359-48b7-917a-abb524287e02"},
    "USER#FIRST_NAME": {"S": "Ryan"},
    "USER#LAST_NAME": {"S": "Rodriguez"},
    "USER#USERNAME": {"S": "Ryan Rodriguez"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#36e6faac-1c63-4f60-8ac2-e4e4efc841b8"},
    "USER#FIRST_NAME": {"S": "Sophia"},
    "USER#LAST_NAME": {"S": "Smith"},
    "USER#USERNAME": {"S": "Sophia Smith"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "USER"},
    "SK_ID": {"S": "USER#88d92a97-f2dd-488a-847c-5d44171db1dd"},
    "USER#FIRST_NAME": {"S": "Thomas"},
    "USER#LAST_NAME": {"S": "Taylor"},
    "USER#USERNAME": {"S": "Thomas Taylor"},
    "USER#IMAGE_URL": {"S": ""},
    "USER#ROLE": {"S": "CLIENT"}
}'

echo "User Items inserted successfully into table $TABLE_NAME."

#Support Agents List

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "SUPPORT_AGENTS"},
    "SK_ID": {"S": "SUPPORT_AGENTS_LIST"},
    "SUPPORT_AGENTS#EMAILS": {"L": [{"S": "example_user1@gmail.com"},{"S": "example_user2@gmail.com"},{"S": "example_user3@gmail.com"},{"S": "example_user4@gmail.com"},{"S": "example_user5@gmail.com"},{"S": "example_user6@gmail.com"},{"S": "example_user7@gmail.com"}]}
}'

echo "Support Agents List Item inserted successfully into table $TABLE_NAME."

#About Us Stories

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "ABOUT_US"},
    "SK_ID": {"S": "ABOUT_US#1"},
    "ABOUT_US#DESCRIPTION": {"S": "in car rentals highlights a steadfast commitment to excellence, marked by a track record of trust and satisfaction among thousands of clients worldwide"},
    "ABOUT_US#NUMERIC_VALUE": {"S": "15"},
    "ABOUT_US#TITLE": {"S": "years"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "ABOUT_US"},
    "SK_ID": {"S": "ABOUT_US#2"},
    "ABOUT_US#DESCRIPTION": {"S": "we make car rentals accessible and convenient for customers no matter where their travels take them, ensuring quality service and easy access"},
    "ABOUT_US#NUMERIC_VALUE": {"S": "6"},
    "ABOUT_US#TITLE": {"S": "locations"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "ABOUT_US"},
    "SK_ID": {"S": "ABOUT_US#3"},
    "ABOUT_US#DESCRIPTION": {"S": "we cater to every kind of traveler, from business professionals to families and adventure seekers, ensuring the perfect vehicle is always available"},
    "ABOUT_US#NUMERIC_VALUE": {"S": "25"},
    "ABOUT_US#TITLE": {"S": "car brands"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "ABOUT_US"},
    "SK_ID": {"S": "ABOUT_US#4"},
    "ABOUT_US#DESCRIPTION": {"S": "we cater to every kind of traveler, from business professionals to families and adventure seekers, ensuring the perfect vehicle is always available"},
    "ABOUT_US#NUMERIC_VALUE": {"S": "100+"},
    "ABOUT_US#TITLE": {"S": "cars"}
}'

echo "AboutUs Items inserted successfully into table $TABLE_NAME."

#FAQ Stories

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FAQ"},
    "SK_ID": {"S": "FAQ#1"},
    "FAQ#ANSWER": {"S": "To rent a car, you will need a valid driver'\''s license, a credit card in your name, and a government-issued photo ID (such as a passport or national ID). International renters may also need to present an International Driving Permit (IDP) in addition to their home country driver'\''s license."},
    "FAQ#QUESTION": {"S": "What documents do I need to rent a car?"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FAQ"},
    "SK_ID": {"S": "FAQ#2"},
    "FAQ#ANSWER": {"S": "Yes, there is an age requirement to rent a car. Typically, the minimum age to rent a car is 21 years old. However, some locations may have different age requirements, and renters under 25 years old may be subject to additional fees and restrictions. It'\''s always best to check with the specific rental location for their policies regarding age requirements. Thank you for your inquiry, and we'\''re here to assist you with any further questions."},
    "FAQ#QUESTION": {"S": "Is there an age requirement to rent a car?"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FAQ"},
    "SK_ID": {"S": "FAQ#3"},
    "FAQ#ANSWER": {"S": "Yes, you can add an additional driver to your rental. Please make sure the additional driver is present with you at the time of rental pickup to provide their driver’s license and other necessary information. Additional fees may apply, and all drivers must meet our age and licensing requirements. Thank you for choosing our car rental service, and we look forward to making your rental experience as smooth as possible."},
    "FAQ#QUESTION": {"S": "Can I add an additional driver to my rental?"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FAQ"},
    "SK_ID": {"S": "FAQ#4"},
    "FAQ#ANSWER": {"S": "In the event of a breakdown or an accident, your safety is our top priority. Please follow these steps:\n1. Ensure your safety and the safety of your passengers. Move to a safe location away from traffic if possible.\n2. Contact emergency services immediately if there are any injuries or if the situation requires police involvement.\n3. Once you are in a safe location and have addressed any immediate safety concerns, contact the rental car company using the emergency or roadside assistance number provided in your rental agreement. They will guide you on the next steps, which may include arranging for a tow, repairs, or a replacement vehicle.\n4. If the situation involves another party, exchange contact and insurance information, and make a note of the accident details, including date, time, location, and any witnesses.\n5. Document the incident by taking photos of the damage and the accident scene if it is safe to do so.\n6. Follow up with the rental car company to complete any necessary paperwork and to address further requirements such as filing an insurance claim.\nWe are here to assist you 24/7, so please do not hesitate to reach out to us in case of any issues with your rental car."},
    "FAQ#QUESTION": {"S": "What should I do if the rental car breaks down or I get into an accident?"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FAQ"},
    "SK_ID": {"S": "FAQ#5"},
    "FAQ#ANSWER": {"S": "The mileage policy can vary depending on the rental company and the specific rental agreement. Some rentals come with unlimited mileage, while others may have a daily mileage limit. If your rental has a mileage limit, exceeding it could result in additional charges per mile.\n\nI recommend checking your rental agreement for details on the mileage policy applicable to your rental. If you anticipate driving long distances and are unsure about the mileage policy, please contact us directly before starting your journey. We are more than happy to clarify and, if necessary, discuss options that include higher mileage limits to better suit your travel needs."},
    "FAQ#QUESTION": {"S": "Is there a mileage limit on my rental?"}
}'

echo "FAQ Items inserted successfully into table $TABLE_NAME."

#Locations

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "LOCATION#NAME": {"S": "Kyiv, Hayatt Hotel"},
    "LOCATION#ADDRESS": {"S": "5, Ally Tarasovoy st"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#26979fed-8e9a-429f-810f-2fce633ad01e"},
    "LOCATION#NAME": {"S": "Kyiv, Opera Hotel"},
    "LOCATION#ADDRESS": {"S": "53, Volodymyrska st"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "LOCATION#NAME": {"S": "Kyiv, Premier Palace Hotel"},
    "LOCATION#ADDRESS": {"S": "5-7/29, T. Shevchenka Blvd"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#4f4b5e1d-841f-4006-b29c-5b0e8724ad74"},
    "LOCATION#NAME": {"S": "Kyiv, Hilton Hotel"},
    "LOCATION#ADDRESS": {"S": "30, Tarasa Shevchenko Blvd"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#28b2926a-7d91-45ec-9957-4c910d30dced"},
    "LOCATION#NAME": {"S": "Kyiv, Radisson Blu Hotel"},
    "LOCATION#ADDRESS": {"S": "22, Yaroslaviv Val st"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#f5445579-8c1d-4962-8b55-f27d49922da9"},
    "LOCATION#NAME": {"S": "Kyiv, InterContinental Hotel"},
    "LOCATION#ADDRESS": {"S": "2A, Velyka Zhytomyrska st"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "LOCATION"},
    "SK_ID": {"S": "LOCATION#2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "LOCATION#NAME": {"S": "Kyiv, Fairmont Grand Hotel"},
    "LOCATION#ADDRESS": {"S": "1, Naberezhno-Khreshchatytska st"},
    "LOCATION#IMAGE_URL": {"S": ""},
    "LOCATION#SUPPORT_AGENT_ID": {"S": ""}
}'

echo "Location Items inserted successfully into table $TABLE_NAME."

#Cars

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#6c59dd64-b30e-4fe2-ba4c-ec663a7afff0"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Audi A6 Quattro 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "150"},
    "CAR#CATEGORY": {"S": "ECONOMY"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "10.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "05.01.2025"},{"S": "06.01.2025"},{"S": "07.01.2025"},{"S": "08.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#f321db64-1c2b-4f32-a212-8f9b2c5f1d1a"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Toyota Corolla 2022"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "80"},
    "CAR#CATEGORY": {"S": "ECONOMY"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "1.8"},
    "CAR#FUEL_CONSUMPTION": {"S": "6.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.5"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "09.01.2025"},{"S": "10.01.2025"},{"S": "11.01.2025"},{"S": "12.01.2025"},{"S": "13.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#b43e6d90-dc7c-4d8c-9e35-91e5767bfb6c"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Tesla Model 3 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "200"},
    "CAR#CATEGORY": {"S": "ELECTRIC"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}, {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}]},
    "CAR#ENGINE_CAPACITY": {"S": "Electric"},
    "CAR#FUEL_CONSUMPTION": {"S": "15 kWh/100km"},
    "CAR#FUEL_TYPE": {"S": "ELECTRIC"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "05.01.2025"}, {"S": "06.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#d50a1c92-f6cb-43e5-82d9-93e4c2f1a7b2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "BMW X5 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "300"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}, {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 Turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "05.01.2025"},{"S": "06.01.2025"},{"S": "07.01.2025"},{"S": "08.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#e78d34fc-a456-47e1-a99f-2e934f1c6d4e"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Ford Transit 2020"},
    "CAR#STATUS": {"S": "UNAVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "120"},
    "CAR#CATEGORY": {"S": "MINIVAN"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.2"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.8 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "MANUAL"},
    "CAR#PASSENGER_CAPACITY": {"S": "8"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.2"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "15.01.2025"},{"S": "16.01.2025"},{"S": "17.01.2025"},{"S": "18.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#a67c9d8e-23f4-4e45-891f-1f23a4b5c6de"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Volkswagen Tiguan 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "180"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}, {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "25.01.2025"},{"S": "26.01.2025"},{"S": "27.01.2025"},{"S": "28.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#15c3ad45-e7b3-4f4a-9913-f7bfb04b45b1"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "BMW 5 Series 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "220"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "01.02.2025"},{"S": "02.02.2025"},{"S": "03.02.2025"},{"S": "04.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#72bfc34f-b8c5-4737-bf49-204de8c8e7f7"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Toyota RAV4 Hybrid 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "180"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Hybrid"},
    "CAR#FUEL_CONSUMPTION": {"S": "5.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "HYBRID"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.01.2025"},{"S": "11.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#1a49f5ad-702e-4e78-abae-b1b2f62de63c"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Ford Mustang Mach-E 2024"},
    "CAR#STATUS": {"S": "UNAVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "250"},
    "CAR#CATEGORY": {"S": "ELECTRIC"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}, {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}]},
    "CAR#ENGINE_CAPACITY": {"S": "Electric Motor"},
    "CAR#FUEL_CONSUMPTION": {"S": "20 kWh/100km"},
    "CAR#FUEL_TYPE": {"S": "ELECTRIC"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.02.2025"},{"S": "11.02.2025"},{"S": "12.02.2025"},{"S": "13.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Honda Odyssey 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "170"},
    "CAR#CATEGORY": {"S": "MINIVAN"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}, {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.5 V6"},
    "CAR#FUEL_CONSUMPTION": {"S": "9.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": []}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#f3a7619e-d88e-4938-b9f5-d184d4e7a8f4"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Mazda CX-5 2024"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "200"},
    "CAR#CATEGORY": {"S": "COMFORT"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.7 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "MANUAL"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "09.01.2025"},{"S": "10.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#91c2fa34-5d9b-4e9d-8f2e-cf2a69eb13c8"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Tesla Model 3 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "300"},
    "CAR#CATEGORY": {"S": "ELECTRIC"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}, {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}]},
    "CAR#ENGINE_CAPACITY": {"S": "Electric Motor"},
    "CAR#FUEL_CONSUMPTION": {"S": "18 kWh/100km"},
    "CAR#FUEL_TYPE": {"S": "ELECTRIC"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "26.01.2025"},{"S": "27.01.2025"},{"S": "28.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#7b3f7e68-8e12-4e3b-9182-30a4c8716ed4"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Audi Q7 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "270"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 Turbo Diesel"},
    "CAR#FUEL_CONSUMPTION": {"S": "10.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "08.01.2025"},{"S": "09.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#2c7f5e3d-9fa2-4c1b-a8c4-11c39db12b74"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Chevrolet Tahoe 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "240"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "5.3 V8"},
    "CAR#FUEL_CONSUMPTION": {"S": "14.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "8"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "06.01.2025"},{"S": "07.01.2025"},{"S": "08.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#8e23c47a-6c8f-4f7b-b7f9-214de39d51b8"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Subaru Outback 2024"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "190"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.4 Turbo"},
    "CAR#FUEL_CONSUMPTION": {"S": "9.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.5"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "12.01.2025"},{"S": "13.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#ab2c7f65-43d8-4f98-b13f-21d9e23487c4"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Nissan Altima 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "160"},
    "CAR#CATEGORY": {"S": "BUSINESS"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}, {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "MANUAL"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.01.2025"},{"S": "11.01.2025"},{"S": "12.01.2025"},{"S": "13.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Honda Accord 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "180"},
    "CAR#CATEGORY": {"S": "BUSINESS"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}, {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}]},
    "CAR#ENGINE_CAPACITY": {"S": "1.5 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "6.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": []}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#5e6f4d89-1a23-4b87-a6e4-b23f91d7c8a3"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Toyota Camry 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "200"},
    "CAR#CATEGORY": {"S": "BUSINESS"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.5"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.01.2025"}, {"S": "11.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#8f91b6a3-43e5-4b7c-a2e3-bc54d8a76e9f"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Ford Explorer 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "250"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}, {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 EcoBoost V6"},
    "CAR#FUEL_CONSUMPTION": {"S": "11.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "02.02.2025"},{"S": "03.02.2025"},{"S": "04.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#a6f7c91b-4d3f-43e8-b65d-7c8a91e5f32b"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "BMW X5 2024"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "320"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 Diesel"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "15.01.2025"}, {"S": "16.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#b91f2a7c-8e65-4b3f-a6d7-c8a5e23f9b1a"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Hyundai Tucson 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "220"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Diesel"},
    "CAR#FUEL_CONSUMPTION": {"S": "6.8 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.02.2025"},{"S": "11.02.2025"},{"S": "12.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#c3b4e6d9-1f7a-42c8-a65d-3e4f8a9b2d7c"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Audi Q7 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "280"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 TFSI V6"},
    "CAR#FUEL_CONSUMPTION": {"S": "9.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "01.02.2025"},{"S": "02.02.2025"},{"S": "03.02.2025"},{"S": "04.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#d7e8b4a1-9f32-4c6b-a65d-2e4c91b3f7a9"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Chevrolet Tahoe 2024"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "300"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}, {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}]},
    "CAR#ENGINE_CAPACITY": {"S": "5.3 V8"},
    "CAR#FUEL_CONSUMPTION": {"S": "14.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "12.01.2025"}, {"S": "13.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#f7b6c3a9-2e5d-4b1f-a6e4-3d8b91c7e6f5"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Nissan Rogue 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "150"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}, {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.8 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.4"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "07.01.2025"},{"S": "08.01.2025"},{"S": "09.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#e3d7b5c9-2a6f-4b1e-a8c5-3b91f7a6c4e2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Subaru Outback 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "170"},
    "CAR#CATEGORY": {"S": "MINIVAN"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.4 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "24.01.2025"},{"S": "25.01.2025"},{"S": "26.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#f8d7b1c5-2e6a-4c9e-a65b-3f91c7e4b8a3"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Volkswagen Tiguan 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "190"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}, {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "13.01.2025"},{"S": "14.01.2025"},{"S": "15.01.2025"},{"S": "16.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#1e4f7b3c-2a9d-4b6e-a7c5-3d8b91f6c4e2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Mazda CX-5 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "160"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.2 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.5"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "07.01.2025"},{"S": "08.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#2b5f8c7d-3e4a-42b9-a6c5-4f7d91b3e6a1"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Ford Explorer 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "240"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "3.0 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "11.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "09.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#3c7e91b2-6a5f-42d9-a65b-4b8f91c4d7a9"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Hyundai Santa Fe 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "200"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.2 Diesel"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "7"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "11.02.2025"},{"S": "12.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#4b6e91f3-5d7a-42c9-a8c5-3e6f91a4b7d2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Toyota RAV4 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "180"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}, {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Hybrid"},
    "CAR#FUEL_CONSUMPTION": {"S": "6.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "HYBRID"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.8"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "04.02.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#5f8e91c6-7b3a-42d9-a6c5-3b4f91e7a9d3"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Kia Sportage 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "170"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "28b2926a-7d91-45ec-9957-4c910d30dced"}, {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Diesel"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.3 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "DIESEL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.4"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "15.01.2025"}, {"S": "16.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#6c7b91e5-3d4f-42a8-a65f-4e6f91b3c7a1"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Honda CR-V 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "190"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "f5445579-8c1d-4962-8b55-f27d49922da9"}, {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}]},
    "CAR#ENGINE_CAPACITY": {"S": "1.5 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "7.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.6"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "03.03.2025"},{"S": "06.03.2025"},{"S": "07.03.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#7d4b91f3-6e7a-42a9-a65f-3b8f91c4d7a2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Subaru Forester 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "185"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"}, {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"}, {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.7"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "13.01.2025"},{"S": "14.01.2025"},{"S": "17.01.2025"},{"S": "18.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#8b6f91e4-5c3d-42a9-a7c5-3e8f91b4d7a2"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Volkswagen Tiguan 2023"},
    "CAR#STATUS": {"S": "BOOKED"},
    "CAR#PRICE_PER_DAY": {"N": "200"},
    "CAR#CATEGORY": {"S": "CROSSOVER"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "9.0 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.5"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "22.01.2025"}, {"S": "23.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#9c4b91f7-6e3a-42a9-a6c5-3b7f91a8d5a3"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "BMW X3 2024"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "280"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},{"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.5 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "10.5 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "AIR_CONDITIONER"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "4.9"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "03.01.2025"},{"S": "04.01.2025"},{"S": "05.01.2025"},{"S": "10.01.2025"},{"S": "11.01.2025"},{"S": "12.01.2025"}]}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "CAR"},
    "SK_ID": {"S": "CAR#10f7e91c-4d5a-42b9-a7c5-3e6f91a4b8d7"},
    "CAR#NUMBERS": {"S": "112233"},
    "CAR#MODEL": {"S": "Mercedes-Benz GLC 2023"},
    "CAR#STATUS": {"S": "AVAILABLE"},
    "CAR#PRICE_PER_DAY": {"N": "310"},
    "CAR#CATEGORY": {"S": "PREMIUM"},
    "CAR#IMAGE_URL": {"S": ""},
    "CAR#IMAGES": {"L": []},
    "CAR#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "CAR#DROPOFF_LOCATIONS_IDS": {"L": [{"S": "26979fed-8e9a-429f-810f-2fce633ad01e"}, {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"}, {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"}]},
    "CAR#ENGINE_CAPACITY": {"S": "2.0 Turbo Petrol"},
    "CAR#FUEL_CONSUMPTION": {"S": "8.8 Liter/100km"},
    "CAR#FUEL_TYPE": {"S": "PETROL"},
    "CAR#CLIMATE_CONTROL_OPTION": {"S": "TWO_ZONE_CLIMATE_CONTROL"},
    "CAR#GEAR_BOX_TYPE": {"S": "AUTOMATIC"},
    "CAR#PASSENGER_CAPACITY": {"S": "5"},
    "CAR#RENTAL_EXPERIENCE": {"S": "5.0"},
    "CAR#BOOKED_DAYS": {"L": [{"S": "10.01.2025"},{"S": "11.01.2025"},{"S": "14.01.2025"},{"S": "15.01.2025"},{"S": "18.01.2025"},{"S": "19.01.2025"},{"S": "20.01.2025"}]}
}'

echo "Car Items inserted successfully into table $TABLE_NAME."

#Bookings

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#e5d2516d-4efe-4360-8f7f-3b8fbd700550"},
    "BOOKING#NUMBER": {"N": "1"},
    "BOOKING#ORDER_DETAILS": {"S": "#1 (30.11.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-11-30 10:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-11-30 22:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-01 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-05 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#b988b091-00e9-4b90-a330-ab49a3b4debf"},
    "BOOKING#NUMBER": {"N": "2"},
    "BOOKING#ORDER_DETAILS": {"S": "#2 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-12-05 18:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-06 04:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-07 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-09 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#20a403e1-77e1-4509-9110-27f5c0873a47"},
    "BOOKING#NUMBER": {"N": "3"},
    "BOOKING#ORDER_DETAILS": {"S": "#3 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-12-07 15:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-08 03:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-11 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-13 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#d7b6d362-1529-473f-bf51-a9a1f105fd71"},
    "BOOKING#NUMBER": {"N": "3"},
    "BOOKING#ORDER_DETAILS": {"S": "#4 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-12-12 21:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-13 10:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-14 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-18 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#c53d8fa9-261b-496e-bbc5-7ebda887ebfe"},
    "BOOKING#NUMBER": {"N": "4"},
    "BOOKING#ORDER_DETAILS": {"S": "#5 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-12-17 09:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-17 21:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-19 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-25 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#d5a26402-1166-4b3d-81ec-20290c539120"},
    "BOOKING#NUMBER": {"N": "5"},
    "BOOKING#ORDER_DETAILS": {"S": "#6 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "BOOKING#CREATED_AT": {"S": "2024-12-24 12:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-25 00:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-26 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-29 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#95e11f3e-d4bf-49d4-b085-f1be9c7de3a3"},
    "BOOKING#NUMBER": {"N": "6"},
    "BOOKING#ORDER_DETAILS": {"S": "#7 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-05 14:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-06 02:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-07 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-08 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#e0c8bf22-4908-4f6e-84c2-9aed9871da6a"},
    "BOOKING#NUMBER": {"N": "7"},
    "BOOKING#ORDER_DETAILS": {"S": "#8 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-08 16:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-09 04:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-10 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-12 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#12cb65b6-ff09-4b85-9d44-3bc283d7ddf9"},
    "BOOKING#NUMBER": {"N": "8"},
    "BOOKING#ORDER_DETAILS": {"S": "#9 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-12 18:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-13 06:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-15 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-18 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#6c5d1082-bc29-4d57-8255-42f4da88bd56"},
    "BOOKING#NUMBER": {"N": "9"},
    "BOOKING#ORDER_DETAILS": {"S": "#10 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-17 20:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-18 08:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-21 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-24 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#5d16487e-a5a6-4aa8-83fe-248d08e289ac"},
    "BOOKING#NUMBER": {"N": "10"},
    "BOOKING#ORDER_DETAILS": {"S": "#11 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-19 22:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-20 10:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-26 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-26 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#d97b1b66-55d3-4389-8e66-25d6e9ead1cc"},
    "BOOKING#NUMBER": {"N": "11"},
    "BOOKING#ORDER_DETAILS": {"S": "#12 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "BOOKING_FINISHED"},
    "BOOKING#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "BOOKING#CREATED_AT": {"S": "2024-12-21 00:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2024-12-22 06:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2024-12-27 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2024-12-29 23:59:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#323a282a-7a51-4998-bfb3-83411a2beb83"},
    "BOOKING#NUMBER": {"N": "12"},
    "BOOKING#ORDER_DETAILS": {"S": "#13 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "SERVICE_STARTED"},
    "BOOKING#CUSTOMER_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "BOOKING#SERVICE_ID": {"S": "6c59dd64-b30e-4fe2-ba4c-ec663a7afff0"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-03 02:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-04 14:00:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2025-01-05 00:00:00"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2025-01-08 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#1dd9dd4d-f62e-4eb7-8da6-6fe6e87ef3b6"},
    "BOOKING#NUMBER": {"N": "13"},
    "BOOKING#ORDER_DETAILS": {"S": "#14 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CUSTOMER_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "BOOKING#SERVICE_ID": {"S": "321db64-1c2b-4f32-a212-8f9b2c5f1d1a"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-07 04:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-07 16:00:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2025-01-09 00:00:00"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2025-01-13 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#28f8acde-be33-4027-8238-da285c4d3cc1"},
    "BOOKING#NUMBER": {"N": "14"},
    "BOOKING#ORDER_DETAILS": {"S": "#15 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CUSTOMER_ID": {"S": "66b313cb-2941-4b1b-9a35-dc6bbcb1fcb9"},
    "BOOKING#SERVICE_ID": {"S": "b43e6d90-dc7c-4d8c-9e35-91e5767bfb6c"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-02 06:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-02 18:00:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2025-01-05 00:00:00"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2025-01-06 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#5e4bd44d-2dc8-4b3c-881d-9369d1094df7"},
    "BOOKING#NUMBER": {"N": "15"},
    "BOOKING#ORDER_DETAILS": {"S": "#16 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED_BY_SUPPORT_AGENT"},
    "BOOKING#CUSTOMER_ID": {"S": "cb8799fd-a94a-46a1-90ce-ba4533ffed9f"},
    "BOOKING#SERVICE_ID": {"S": "d50a1c92-f6cb-43e5-82d9-93e4c2f1a7b2"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-03 08:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-03 20:00:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2025-01-05 00:00:00"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2025-01-08 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#7d9d4208-aa0a-4f59-aff0-d74c03baf085"},
    "BOOKING#NUMBER": {"N": "16"},
    "BOOKING#ORDER_DETAILS": {"S": "#17 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CUSTOMER_ID": {"S": "f6a07d92-3ea4-4a11-94f1-5982cfd23650"},
    "BOOKING#SERVICE_ID": {"S": "e78d34fc-a456-47e1-a99f-2e934f1c6d4e"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-12 10:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-12 22:00:00"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2025-01-15 00:00:00"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2025-01-18 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "f5445579-8c1d-4962-8b55-f27d49922da9"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#9ceabb7f-999a-4f99-b4b4-47d1412483af"},
    "BOOKING#NUMBER": {"N": "17"},
    "BOOKING#ORDER_DETAILS": {"S": "#18 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CLIENT_ID": {"S": "76c1690c-b960-43ef-bc0b-fc712c74a845"},
    "BOOKING#CAR_ID": {"S": "a67c9d8e-23f4-4e45-891f-1f23a4b5c6de"},
    "BOOKING#CREATED_AT": {"S": "2025-01-21 12:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-22 00:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-25 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-28 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#24cd7e00-e3f2-4e8b-b8bd-ce76b7f947a9" },
    "BOOKING#NUMBER": {"N": "18"},
    "BOOKING#ORDER_DETAILS": { "S": "#19 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "80bb1573-5d62-419a-806c-4f55b8936414" },
    "BOOKING#CAR_ID": { "S": "15c3ad45-e7b3-4f4a-9913-f7bfb04b45b1" },
    "BOOKING#CREATED_AT": { "S": "2025-01-28 14:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-29 02:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-02-01 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-02-04 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#87f098fe-a83d-4bac-9542-0cf8dfdd74c0" },
    "BOOKING#NUMBER": {"N": "19"},
    "BOOKING#ORDER_DETAILS": { "S": "#20 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "ffa90dc8-760a-46d7-bec2-6019ef9b8450" },
    "BOOKING#CAR_ID": { "S": "72bfc34f-b8c5-4737-bf49-204de8c8e7f7" },
    "BOOKING#CREATED_AT": { "S": "2025-01-07 16:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-08 04:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-11 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#f2b63fba-0747-42c7-b090-3734b772b77b" },
    "BOOKING#NUMBER": {"N": "20"},
    "BOOKING#ORDER_DETAILS": { "S": "#21 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "a965798d-ec20-48dd-a0f3-03e60a24b85d" },
    "BOOKING#CAR_ID": { "S": "1a49f5ad-702e-4e78-abae-b1b2f62de63c" },
    "BOOKING#CREATED_AT": { "S": "2025-02-06 18:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-02-07 06:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-02-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-02-13 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#6fa68da9-539e-45eb-b1f6-8be5a940351e" },
    "BOOKING#NUMBER": {"N": "21"},
    "BOOKING#ORDER_DETAILS": { "S": "#22 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "fe922b05-b460-4ea9-8780-aca68d337916" },
    "BOOKING#CAR_ID": { "S": "f3a7619e-d88e-4938-b9f5-d184d4e7a8f4" },
    "BOOKING#CREATED_AT": { "S": "2025-01-06 20:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-07 08:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-09 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-10 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "f5445579-8c1d-4962-8b55-f27d49922da9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#dcd94bdf-1e78-4c93-bf14-719920ee9eb3" },
    "BOOKING#NUMBER": {"N": "22"},
    "BOOKING#ORDER_DETAILS": { "S": "#23 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "4b9a9ea1-32aa-4cde-929c-3748af4302df" },
    "BOOKING#CAR_ID": { "S": "91c2fa34-5d9b-4e9d-8f2e-cf2a69eb13c8" },
    "BOOKING#CREATED_AT": { "S": "2025-01-24 22:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-25 10:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-26 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-28 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#811e8750-09df-4a80-8dee-6766f97b5402" },
    "BOOKING#NUMBER": {"N": "23"},
    "BOOKING#ORDER_DETAILS": { "S": "#24 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "2ac8c915-7c40-4a1e-b072-19ba15825f5b" },
    "BOOKING#CAR_ID": { "S": "7b3f7e68-8e12-4e3b-9182-30a4c8716ed4" },
    "BOOKING#CREATED_AT": { "S": "2025-01-06 00:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-06 12:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-08 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-09 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#445456e7-3229-439f-8304-208ad705b148" },
    "BOOKING#NUMBER": {"N": "24"},
    "BOOKING#ORDER_DETAILS": { "S": "#25 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "4733dc70-6c88-4f05-897c-b4c0820b10cc" },
    "BOOKING#CAR_ID": { "S": "2c7f5e3d-9fa2-4c1b-a8c4-11c39db12b74" },
    "BOOKING#CREATED_AT": { "S": "2025-01-04 02:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-04 14:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-06 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-08 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#13b4d6c0-9d96-4aa4-a1ce-f96e8ef377fa" },
    "BOOKING#NUMBER": {"N": "25"},
    "BOOKING#ORDER_DETAILS": { "S": "#26 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "e593163a-96f5-48d2-b35f-63ba283d1e6c" },
    "BOOKING#CAR_ID": { "S": "8e23c47a-6c8f-4f7b-b7f9-214de39d51b8" },
    "BOOKING#CREATED_AT": { "S": "2025-01-11 04:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-11 16:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-12 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-13 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#639f0cae-9344-479e-9d4f-671007158aa2" },
    "BOOKING#NUMBER": {"N": "26"},
    "BOOKING#ORDER_DETAILS": { "S": "#27 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "df5b0680-1603-4ca3-8571-5a0e4fe4c0d6" },
    "BOOKING#CAR_ID": { "S": "ab2c7f65-43d8-4f98-b13f-21d9e23487c4" },
    "BOOKING#CREATED_AT": { "S": "2025-01-09 06:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-09 18:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-13 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "28b2926a-7d91-45ec-9957-4c910d30dced" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#076edf80-229b-47cf-8daa-9efa682f9342" },
    "BOOKING#NUMBER": {"N": "27"},
    "BOOKING#ORDER_DETAILS": { "S": "#28 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "49e4daf7-a43c-4d2a-a260-41a6d4a82a59" },
    "BOOKING#CAR_ID": { "S": "5e6f4d89-1a23-4b87-a6e4-b23f91d7c8a3" },
    "BOOKING#CREATED_AT": { "S": "2025-01-08 08:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-08 20:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-11 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "f5445579-8c1d-4962-8b55-f27d49922da9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#623e65b3-f362-4733-bffe-d3f64ea698bf" },
    "BOOKING#NUMBER": {"N": "28"},
    "BOOKING#ORDER_DETAILS": { "S": "#29 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "fabbaf9e-5473-4965-9d40-9b3b59862e29" },
    "BOOKING#CAR_ID": { "S": "8f91b6a3-43e5-4b7c-a2e3-bc54d8a76e9f" },
    "BOOKING#CREATED_AT": { "S": "2025-01-30 10:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-30 22:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-02-02 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-02-04 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#dac2d6f4-df68-49d5-81e5-0853837963bc" },
    "BOOKING#NUMBER": {"N": "29"},
    "BOOKING#ORDER_DETAILS": { "S": "#30 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "d2162054-3359-48b7-917a-abb524287e02" },
    "BOOKING#CAR_ID": { "S": "a6f7c91b-4d3f-43e8-b65d-7c8a91e5f32b" },
    "BOOKING#CREATED_AT": { "S": "2025-01-12 12:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-13 00:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-15 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-16 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#c6cc4791-6974-4ab6-90a7-ea308f851624" },
    "BOOKING#NUMBER": {"N": "30"},
    "BOOKING#ORDER_DETAILS": { "S": "#31 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "36e6faac-1c63-4f60-8ac2-e4e4efc841b8" },
    "BOOKING#CAR_ID": { "S": "b91f2a7c-8e65-4b3f-a6d7-c8a5e23f9b1a" },
    "BOOKING#CREATED_AT": { "S": "2025-02-05 14:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-02-06 02:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-02-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-02-12 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#b3d65454-d325-428b-a8da-f0892a807fbf" },
    "BOOKING#NUMBER": {"N": "31"},
    "BOOKING#ORDER_DETAILS": { "S": "#32 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "88d92a97-f2dd-488a-847c-5d44171db1dd" },
    "BOOKING#CAR_ID": { "S": "c3b4e6d9-1f7a-42c8-a65d-3e4f8a9b2d7c" },
    "BOOKING#CREATED_AT": { "S": "2025-01-25 16:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-26 04:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-02-01 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-02-04 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#1f82486f-5f26-4dc2-94b5-b4ef835e7639" },
    "BOOKING#NUMBER": {"N": "32"},
    "BOOKING#ORDER_DETAILS": { "S": "#33 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "a965798d-ec20-48dd-a0f3-03e60a24b85d" },
    "BOOKING#CAR_ID": { "S": "d7e8b4a1-9f32-4c6b-a65d-2e4c91b3f7a9" },
    "BOOKING#CREATED_AT": { "S": "2025-01-03 18:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-04 06:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-12 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-13 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "28b2926a-7d91-45ec-9957-4c910d30dced" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#86cf4126-8766-40e6-a362-163b30a7ae8e" },
    "BOOKING#NUMBER": {"N": "33"},
    "BOOKING#ORDER_DETAILS": { "S": "#34 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "fe922b05-b460-4ea9-8780-aca68d337916" },
    "BOOKING#CAR_ID": { "S": "f7b6c3a9-2e5d-4b1f-a6e4-3d8b91c7e6f5" },
    "BOOKING#CREATED_AT": { "S": "2025-01-04 20:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-05 08:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-07 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-09 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "28b2926a-7d91-45ec-9957-4c910d30dced" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#fd06f8fd-8e39-46db-9baa-d7a00a1828d7" },
    "BOOKING#NUMBER": {"N": "34"},
    "BOOKING#ORDER_DETAILS": { "S": "#35 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "4b9a9ea1-32aa-4cde-929c-3748af4302df" },
    "BOOKING#CAR_ID": { "S": "e3d7b5c9-2a6f-4b1e-a8c5-3b91f7a6c4e2" },
    "BOOKING#CREATED_AT": { "S": "2025-01-21 22:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-22 10:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-24 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-26 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "f5445579-8c1d-4962-8b55-f27d49922da9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#0955e9f3-c9c9-4121-aa3e-3fee442f2b1f" },
    "BOOKING#NUMBER": {"N": "35"},
    "BOOKING#ORDER_DETAILS": { "S": "#36 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "2ac8c915-7c40-4a1e-b072-19ba15825f5b" },
    "BOOKING#CAR_ID": { "S": "f8d7b1c5-2e6a-4c9e-a65b-3f91c7e4b8a3" },
    "BOOKING#CREATED_AT": { "S": "2025-01-12 00:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-12 12:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-13 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-16 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#58ff06aa-a031-4f3e-9ebc-e1617dc7e058"},
    "BOOKING#NUMBER": {"N": "36"},
    "BOOKING#ORDER_DETAILS": {"S": "#37 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED_BY_SUPPORT_AGENT"},
    "BOOKING#CLIENT_ID": {"S": "4733dc70-6c88-4f05-897c-b4c0820b10cc"},
    "BOOKING#CAR_ID": {"S": "1e4f7b3c-2a9d-4b6e-a7c5-3d8b91f6c4e2"},
    "BOOKING#CREATED_AT": {"S": "2025-01-05 02:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-05 14:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-07 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-08 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#11f803b1-24b1-40ef-b762-3f2bbbe39895"},
    "BOOKING#NUMBER": {"N": "37"},
    "BOOKING#ORDER_DETAILS": {"S": "#38 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CLIENT_ID": {"S": "e593163a-96f5-48d2-b35f-63ba283d1e6c"},
    "BOOKING#CAR_ID": {"S": "2b5f8c7d-3e4a-42b9-a6c5-4f7d91b3e6a1"},
    "BOOKING#CREATED_AT": {"S": "2025-01-04 04:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-04 16:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-09 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-09 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#b5ff6385-c18a-47ec-a476-72ba48f9ac67"},
    "BOOKING#NUMBER": {"N": "38"},
    "BOOKING#ORDER_DETAILS": {"S": "#39 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED_BY_SUPPORT_AGENT"},
    "BOOKING#CLIENT_ID": {"S": "df5b0680-1603-4ca3-8571-5a0e4fe4c0d6"},
    "BOOKING#CAR_ID": {"S": "3c7e91b2-6a5f-42d9-a65b-4b8f91c4d7a9"},
    "BOOKING#CREATED_AT": {"S": "2025-02-07 06:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-02-07 18:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-02-11 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-02-12 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#80e65c69-72ee-4999-adf4-6fa7cf6d9655"},
    "BOOKING#NUMBER": {"N": "39"},
    "BOOKING#ORDER_DETAILS": {"S": "#40 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED_BY_SUPPORT_AGENT"},
    "BOOKING#CLIENT_ID": {"S": "49e4daf7-a43c-4d2a-a260-41a6d4a82a59"},
    "BOOKING#CAR_ID": {"S": "4b6e91f3-5d7a-42c9-a8c5-3e6f91a4b7d2"},
    "BOOKING#CREATED_AT": {"S": "2025-02-01 08:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-02-01 20:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-02-04 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-02-04 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "28b2926a-7d91-45ec-9957-4c910d30dced"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#6e41cbde-5438-4bb4-8172-e0aaa74c24a3" },
    "BOOKING#NUMBER": {"N": "40"},
    "BOOKING#ORDER_DETAILS": { "S": "#41 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "fabbaf9e-5473-4965-9d40-9b3b59862e29" },
    "BOOKING#CAR_ID": { "S": "5f8e91c6-7b3a-42d9-a6c5-3b4f91e7a9d3" },
    "BOOKING#CREATED_AT": { "S": "2025-01-10 10:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-10 22:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-15 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-16 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "28b2926a-7d91-45ec-9957-4c910d30dced" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#c7ab2bba-e18b-4c17-9d4a-95a93032ddef" },
    "BOOKING#NUMBER": {"N": "41"},
    "BOOKING#ORDER_DETAILS": { "S": "#42 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "d2162054-3359-48b7-917a-abb524287e02" },
    "BOOKING#CAR_ID": { "S": "6c7b91e5-3d4f-42a8-a65f-4e6f91b3c7a1" },
    "BOOKING#CREATED_AT": { "S": "2025-02-26 12:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-02-27 00:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-03-03 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-03-03 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "f5445579-8c1d-4962-8b55-f27d49922da9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#947e09bf-de09-40f8-8c59-1e47f1d8af9a" },
    "BOOKING#NUMBER": {"N": "42"},
    "BOOKING#ORDER_DETAILS": { "S": "#43 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "36e6faac-1c63-4f60-8ac2-e4e4efc841b8" },
    "BOOKING#CAR_ID": { "S": "6c7b91e5-3d4f-42a8-a65f-4e6f91b3c7a1" },
    "BOOKING#CREATED_AT": { "S": "2025-03-03 14:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-03-04 02:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-03-06 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-03-07 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "f5445579-8c1d-4962-8b55-f27d49922da9" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#7de3f6ea-064e-4a28-bb35-83207e5ba4f8" },
    "BOOKING#NUMBER": {"N": "43"},
    "BOOKING#ORDER_DETAILS": { "S": "#44 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "88d92a97-f2dd-488a-847c-5d44171db1dd" },
    "BOOKING#CAR_ID": { "S": "7d4b91f3-6e7a-42a9-a65f-3b8f91c4d7a2" },
    "BOOKING#CREATED_AT": { "S": "2025-01-09 16:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-10 04:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-13 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-14 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#b0e544ec-7e85-41bf-bd2e-947a558be19c" },
    "BOOKING#NUMBER": {"N": "44"},
    "BOOKING#ORDER_DETAILS": { "S": "#45 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "4733dc70-6c88-4f05-897c-b4c0820b10cc" },
    "BOOKING#CAR_ID": { "S": "7d4b91f3-6e7a-42a9-a65f-3b8f91c4d7a2" },
    "BOOKING#CREATED_AT": { "S": "2025-01-12 18:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-13 06:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-17 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-18 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "2c9b6f42-a3f3-4508-9e1a-d3753cf36292" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#b8a58526-1b16-442c-a093-fd1dc99334fe" },
    "BOOKING#NUMBER": {"N": "45"},
    "BOOKING#ORDER_DETAILS": { "S": "#46 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED" },
    "BOOKING#CLIENT_ID": { "S": "e593163a-96f5-48d2-b35f-63ba283d1e6c" },
    "BOOKING#CAR_ID": { "S": "8b6f91e4-5c3d-42a9-a7c5-3e8f91b4d7a2" },
    "BOOKING#CREATED_AT": { "S": "2025-01-18 20:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-19 08:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-22 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-23 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#b43e71ee-4f96-4d30-a834-3c9bdbc52d2b" },
    "BOOKING#NUMBER": {"N": "46"},
    "BOOKING#ORDER_DETAILS": { "S": "#47 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "df5b0680-1603-4ca3-8571-5a0e4fe4c0d6" },
    "BOOKING#CAR_ID": { "S": "9c4b91f7-6e3a-42a9-a6c5-3b7f91a8d5a3" },
    "BOOKING#CREATED_AT": { "S": "2025-01-02 22:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-03 10:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-03 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-05 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "26979fed-8e9a-429f-810f-2fce633ad01e" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": { "S": "BOOKING" },
    "SK_ID": { "S": "BOOKING#7fe89b28-1544-41f1-b0eb-85cd8cff32c8" },
    "BOOKING#NUMBER": {"N": "47"},
    "BOOKING#ORDER_DETAILS": { "S": "#48 (05.10.2024)" },
    "BOOKING#STATUS": { "S": "RESERVED_BY_SUPPORT_AGENT" },
    "BOOKING#CLIENT_ID": { "S": "49e4daf7-a43c-4d2a-a260-41a6d4a82a59" },
    "BOOKING#CAR_ID": { "S": "9c4b91f7-6e3a-42a9-a6c5-3b7f91a8d5a3" },
    "BOOKING#CREATED_AT": { "S": "2025-01-08 00:00:00" },
    "BOOKING#LOCKED_FROM": { "S": "2025-01-08 12:00:00" },
    "BOOKING#PICKUP_DATE_TIME": { "S": "2025-01-10 00:00:00" },
    "BOOKING#DROPOFF_DATE_TIME": { "S": "2025-01-12 23:59:59" },
    "BOOKING#PICKUP_LOCATION_ID": { "S": "ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4" },
    "BOOKING#DROPOFF_LOCATION_ID": { "S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9" },
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#09872217-44a4-4433-b5ba-ee56ca02f223"},
    "BOOKING#NUMBER": {"N": "48"},
    "BOOKING#ORDER_DETAILS": {"S": "#49 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CLIENT_ID": {"S": "fabbaf9e-5473-4965-9d40-9b3b59862e29"},
    "BOOKING#CAR_ID": {"S": "10f7e91c-4d5a-42b9-a7c5-3e6f91a4b8d7"},
    "BOOKING#CREATED_AT": {"S": "2025-01-07 02:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-07 14:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-10 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-11 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#c372fece-58d2-45d7-9d8c-c575a92a610b"},
    "BOOKING#NUMBER": {"N": "49"},
    "BOOKING#ORDER_DETAILS": {"S": "#50 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED"},
    "BOOKING#CLIENT_ID": {"S": "fe922b05-b460-4ea9-8780-aca68d337916"},
    "BOOKING#CAR_ID": {"S": "10f7e91c-4d5a-42b9-a7c5-3e6f91a4b8d7"},
    "BOOKING#CREATED_AT": {"S": "2025-01-12 04:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-12 16:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-14 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-15 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "2aacd5df-b679-49f4-9c20-ecbead6e6ff9"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "BOOKING"},
    "SK_ID": {"S": "BOOKING#8d730ea2-1a4d-41a4-89fb-6a0ac5e75d39"},
    "BOOKING#NUMBER": {"N": "50"},
    "BOOKING#ORDER_DETAILS": {"S": "#51 (05.10.2024)"},
    "BOOKING#STATUS": {"S": "RESERVED_BY_SUPPORT_AGENT"},
    "BOOKING#CLIENT_ID": {"S": "4b9a9ea1-32aa-4cde-929c-3748af4302df"},
    "BOOKING#CAR_ID": {"S": "10f7e91c-4d5a-42b9-a7c5-3e6f91a4b8d7"},
    "BOOKING#CREATED_AT": {"S": "2025-01-15 06:00:00"},
    "BOOKING#LOCKED_FROM": {"S": "2025-01-15 18:00:00"},
    "BOOKING#PICKUP_DATE_TIME": {"S": "2025-01-18 00:00:00"},
    "BOOKING#DROPOFF_DATE_TIME": {"S": "2025-01-20 23:59:59"},
    "BOOKING#PICKUP_LOCATION_ID": {"S": "26979fed-8e9a-429f-810f-2fce633ad01e"},
    "BOOKING#DROPOFF_LOCATION_ID": {"S": "4f4b5e1d-841f-4006-b29c-5b0e8724ad74"},
    "BOOKING#SUPPORT_AGENT_ID": {"S": ""},
    "BOOKING#CAR_MILEAGE_START": {"S": "0"},
    "BOOKING#CAR_MILEAGE_END": {"S": "1000"}
}'

echo "Booking Items inserted successfully into table $TABLE_NAME."

#Feedbacks

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#b6ee419e-9bb2-42cf-90ef-12d980224acf"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "e5d2516d-4efe-4360-8f7f-3b8fbd700550"},
    "FEEDBACK#DATE": {"S": "06.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.5"},
    "FEEDBACK#TEXT": {"S": "The car rental experience was decent, but I found the customer support response time to be slow. The car was great, but I’d like to see improvements in communication for a better overall experience."}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#2b96783b-5790-4bef-b74f-35adfc4525f7"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "b988b091-00e9-4b90-a330-ab49a3b4debf"},
    "FEEDBACK#DATE": {"S": "11.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.6"},
    "FEEDBACK#TEXT": {"S": "Overall, the service was good, but there was a slight delay during the pickup due to a busy period. However, the staff handled the situation professionally, and the car was in excellent shape."}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#a057074c-6a92-42bd-b495-fffc962660af"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "20a403e1-77e1-4509-9110-27f5c0873a47"},
    "FEEDBACK#DATE": {"S": "14.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.9"},
    "FEEDBACK#TEXT": {"S": "The pickup and return process were seamless and quick. The car was clean and well-maintained, and the staff answered all my questions. Highly recommend this company!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#4cb98cb7-bc9b-43b7-95e5-1329281a03f5"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "d7b6d362-1529-473f-bf51-a9a1f105fd71"},
    "FEEDBACK#DATE": {"S": "20.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "5"},
    "FEEDBACK#TEXT": {"S": "I was impressed with the affordability and reliability of the service. The car ran perfectly, and I had no issues throughout the rental period. Great value for the price!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#9afd91d1-2f1b-453b-aa2f-9a18f745daa4"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "c53d8fa9-261b-496e-bbc5-7ebda887ebfe"},
    "FEEDBACK#DATE": {"S": "26.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.8"},
    "FEEDBACK#TEXT": {"S": "The wide selection of vehicles was a huge plus! I found the perfect car for my trip, and it performed flawlessly. I appreciate their commitment to quality."}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#364f75e2-c3dd-4620-8918-53b6a27c5a91"},
    "FEEDBACK#CAR_ID": {"S": "3457a5d7-c7a8-437f-8fda-fb825d5a2138"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "d5a26402-1166-4b3d-81ec-20290c539120"},
    "FEEDBACK#DATE": {"S": "31.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.8"},
    "FEEDBACK#TEXT": {"S": "I had a fantastic experience renting a car from this service. The booking process was straightforward, the staff was friendly, and the vehicle was in excellent condition. I’ll definitely use them again for my future trips!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#6b568ebd-c48f-43dc-a29b-9062e0eb0772"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "95e11f3e-d4bf-49d4-b085-f1be9c7de3a3"},
    "FEEDBACK#DATE": {"S": "09.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.2"},
    "FEEDBACK#TEXT": {"S": "The car broke down during my trip, and while the support team eventually resolved the issue, it took longer than expected. I’d suggest quicker roadside assistance for emergencies."}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#d8f9c842-1ef0-45e3-b94f-30521d99f2f7"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "e0c8bf22-4908-4f6e-84c2-9aed9871da6a"},
    "FEEDBACK#DATE": {"S": "14.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "5"},
    "FEEDBACK#TEXT": {"S": "The car I rented was spotless and equipped with modern features like GPS and Bluetooth, which made my trip more enjoyable. Excellent attention to detail!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#fca8f941-965b-49e6-9a98-e1f6cd1ac4f8"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "12cb65b6-ff09-4b85-9d44-3bc283d7ddf9"},
    "FEEDBACK#DATE": {"S": "19.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.6"},
    "FEEDBACK#TEXT": {"S": "The staff went above and beyond to ensure I had a great experience. They were friendly, knowledgeable, and quick to address any concerns. Highly recommended!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#9b8a8589-15dc-4019-964b-3072e6565f49"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "6c5d1082-bc29-4d57-8255-42f4da88bd56"},
    "FEEDBACK#DATE": {"S": "26.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "5"},
    "FEEDBACK#TEXT": {"S": "I needed a car at the last minute, and they delivered! The process was fast, and I got a reliable vehicle that made my trip hassle-free. Great service!"}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#7983fa5c-74f8-4d46-83ac-b54f83a02610"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "0264f885-b5af-4633-8742-24dd8c35c12d"},
    "FEEDBACK#BOOKING_ID": {"S": "5d16487e-a5a6-4aa8-83fe-248d08e289ac"},
    "FEEDBACK#DATE": {"S": "27.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.5"},
    "FEEDBACK#TEXT": {"S": "The car was fine, but I was disappointed by unexpected fees that weren’t clearly stated during booking. Transparency in pricing needs improvement."}
}'

aws dynamodb put-item --profile "$AWS_PROFILE" --region "$REGION" --table-name "$TABLE_NAME" --item '{
    "PK_ID": {"S": "FEEDBACK"},
    "SK_ID": {"S": "FEEDBACK#adaa0c80-597f-4004-86ae-898d5e6d9412"},
    "FEEDBACK#CAR_ID": {"S": "9d8b1e23-432f-4a7b-b52e-d6f7b23c71b5"},
    "FEEDBACK#CLIENT_ID": {"S": "34ea8d35-0144-41ff-a5ab-ea4e9836eaf0"},
    "FEEDBACK#BOOKING_ID": {"S": "d97b1b66-55d3-4389-8e66-25d6e9ead1cc"},
    "FEEDBACK#DATE": {"S": "31.12.2024"},
    "FEEDBACK#RENTAL_EXPERIENCE": {"S": "4.7"},
    "FEEDBACK#TEXT": {"S": "I rented a car for a week-long road trip, and it was perfect. The vehicle was fuel-efficient and comfortable, making my journey stress-free. I’ll definitely rent from them again."}
}'

echo "Feedback Items inserted successfully into table $TABLE_NAME."