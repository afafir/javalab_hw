//use javalab

db.consumers.insertMany([
        {
            name: 'Bulat',
            disease: 'Diseaseeeeee'
        },
        {
            name: 'Ne bulat',
            disease: 'disease',
            years: 20
        },
        {
            name: 'yeeep',
            years: 89
        }

    ]
);

db.volunteers.insertMany([
    {
        name: 'Bulat',
        address: {
            city: 'Казань',
            address: 'Улица колотушкина'
        }
    },
    {
        name: 'Ne bulat',
        years: 20,
        address: {
            city: 'Казань',
            address: 'Улица колотушкина'
        }
    },
    {
        name: 'Chelovek',
        years: 15,
        address: {
            city: 'Казань',
            address: 'Улица колотушкина'
        }
    }

]);

db.products.insertMany(
    [   {
        name: "product1",
        count: 2
    },
        {
            name: "product2"

        }]
)

db.tasks.insertMany(
    [
        {
            description:'памагитя',
            consumer: ObjectId('5fbe5ad50143ad094c242563')
        },
        {
            description: 'памагитя234',
            consumer: ObjectId('5fbe5ad50143ad094c242565')
        }]
)

db.tasks.update({_id: ObjectId('5fbe6cd40143ad094c242576')},
    {
        $set:{
            volunteer: ObjectId('5fbe5d790143ad094c242568')
        },
        $push:{
            product: ObjectId('5fbe61b10143ad094c24256d')
        }
    }


)