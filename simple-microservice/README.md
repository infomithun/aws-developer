[Run Redis Stack on Docker | Redis](https://redis.io/docs/stack/get-started/install/docker/)

### Run a redis/redis-stack

To start a Redis Stack container using the `redis-stack` image, run the following command in your terminal:

```bash
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```

The `docker run` command above also exposes RedisInsight on port 8001. You can use RedisInsight by pointing your browser to `localhost:8001`.

The redis-stack is now running as a container - Docker Desktop

### View the inserted records in Redis Stack

Execute the POST request (http://localhost:8080/car) with few data like below:

```json
{
    "id":1,
    "name":"CLA",
    "manufacturer":"Mercedes",
    "type":"Saloon"
}
```

The inserted JSON documents to Redis data can be viewed here:
http://localhost:8001/redis-stack/browser

You can see a HASH created with the name CARS with the records you inserted