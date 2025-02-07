Installation

Get Postman from its website and install it. Go to my published Postman documentation:
https://documenter.getpostman.com/view/41470699/2sAYX6phNj
When you are in the website, click "Run in Postman" in the top right corner. Now you can press "Run in Windows"
You will get a prompt asking if you want to open Postman. Open it and then import into your workspace and continue.
You have now accessed my API.

To try all the requests in Postman you will need to clone my github repository, which you do by going to:
https://github.com/Leo-J-Skola/PlantSwap
Once there, click the green button called <> Code and copy the url: https://github.com/Leo-J-Skola/PlantSwap.git
Now if you are on Windows, go to a folder you want to download my project to and then shift click anywhere inside the folder
and click Terminal. Now inside the command prompt you have to write git clone https://github.com/Leo-J-Skola/PlantSwap.git

Now my project has been cloned and you can now open the project inside IntelliJ.
Inside the project in IntelliJ, start the API locally by pressing the green button with a circular arrow with the text 
"PlantswapApplication" beside it.
Now Postman should be connected to IntelliJ through http://localhost:8080/ and you can start trying out Postman requests.


How to Use the API on Postman

In the left sidebar you can expand the PlantswapAPI collection. There you will see folders for Plants, Users, and Transactions.
Click on any request to see its details (like the URL, method, and body).
Here you can also change the variables (like :userId, :plantId, or :transactionId) as needed.
Click "Send" to run the request and see the response.


How Transactions Work

Trading Plants
You can trade plants with other users. To start a trade, create a trade transaction with your plant.
Another user can offer their plant for your plant. You can also accept or decline the offer.
If you accept, the plant ids are switched, and then the trade is completed, deleting the transaction.

Selling Plants
You can only sell plants for a price between 50 and 1000. Another user can buy your plant if they input
the amount he is selling it for. On a successful buy, their plant ids will be switched again

Transaction Limits
You can only have up to 10 active transactions at a time. All Transactions
are up until completed or deleted.

Trade Offers
Trade offers check for someone elses transaction, and checks ownership or the plant you want to offer.
If your user id and plant id matches, you will send a trade offer to the owner of the transaction.

The owner of the transaction can then either accept or decline the offer.
If accepted, the plant ids are switched again.


Limitations

Users cannot have more than 10 available transactions at once.
You cannot create a transaction for a plant you do not own.

Plants must have an owner for it to be posted as a transaction.

Transactions always check for ownership of transactions or plants,
and you cannot pay more or less than what is being priced.


Known issues

Anyone can edit plant information, user information and transaction information.
No method or POST request to create a plant that is inside a transaction from start.
It's an issue because it's harder to test data from scratch.


What to improve

More validation checks, a lot of duplicated code and more error codes needed.
