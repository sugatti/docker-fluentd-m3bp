# fluentd and m3bp on docker

Example batch application to enter fluentd logs.

* Send a dummy log(sales) from Fluentd.
* Perform sales aggregation by combining received log and master data.

## Prerequisite

* Docker

## Running the application

```
docker-compose up -d
```

When Fluentd starts up, it will be output under the `/directio/sales` directory every 30 seconds with the following file name.

```
ll directio/sales/
total 32
-rw-r--r--  1 suga  staff  4158  4 24 18:36 sales.2019-04-24-1835_0.log
-rw-r--r--  1 suga  staff  4032  4 24 18:37 sales.2019-04-24-1835_1.log
-rw-r--r--  1 suga  staff  3780  4 24 18:37 sales.2019-04-24-1835_2.log
```

Build the [Asakusa on M<sup>3</sup>BP](https://github.com/asakusafw/asakusafw-m3bp) batch application and deploy it to ASAKUSA_HOME.

```
docker-compose run m3bp
```

Execute the Asakusa batch application with the following command to create sales aggregation results for the target date.

```
docker-compose run m3bp asakusa run m3bp.example.summarizeSales -A date=2019-04-24
```

```
cat directio/result/category/result.csv 
カテゴリコード,販売数量,売上合計
1600,850,102000
```

