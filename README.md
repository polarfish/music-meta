# music-meta
Music Video Metadata Management System (test assignment)

### Running the project

###### Linux
```$ ./start.sh```

###### Windows
```$ ./start.bat``` 

The project is configured to work on port 8080

### API usage

The root of the project contains a link to the Postman collection.

* Create entity ```POST api/v1/music-video-metadata```
  * Accepts payload (see entity example below)
  * ID is generated automatically (starting from 100)
  * Returns the created entity
* Read entity ```GET api/v1/music-video-metadata/{id}```
* Update entity ```PUT api/v1/music-video-metadata/{id}```
  * Accepts payload (see entity example below)
  * Returns the updated entity
* Delete entity ```DELETE api/v1/music-video-metadata/{id}```
  * Returns nothing in case of success (just code 200)
* List entities ```GET api/v1/music-video-metadata?genre={genre}&subgenre={subgenre}```
  * *genre* and *subgenre* parameters are optional

### Entity example
```
{
  "title": "Bohemian Rhapsody",
  "album": "A Night at the Opera",
  "artist": "Queen",
  "duration": 367,
  "genre": "Rock",
  "subgenres": [
    "Progressive rock",
    "Classical rock",
    "Art rock",
    "Symphonic rock"
  ],
  "releaseYear": 1975
}
```
