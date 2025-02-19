# Orthologous Stable ID History

A tool for managing orthologous stable identifiers during Reactome release process.

## Requirements

- Java 11+
- MySQL database
- Docker (for containerized execution)

## Quick Start

1. **Configure**: Create `config/auth.properties`:
   ```properties
   dbHost=your_mysql_host
   dbName=your_database_name
   dbUser=your_username
   dbPwd=your_password
   ```

2. **Run with Docker**:
   ```bash
   docker run -v /path/to/config:/opt/release-ortho-stable-id-history/config \
     public.ecr.aws/reactome/release-ortho-stable-id-history:latest \
     java -jar target/OrthoStableIdHistory-*-SNAPSHOT-jar-with-dependencies.jar config/auth.properties
   ```

## Troubleshooting SSL Issues

If experiencing SSL handshake errors:
```bash
java -Dhttps.protocols=TLSv1.2 -Dcom.mysql.jdbc.sslMode=REQUIRED \
  -jar target/OrthoStableIdHistory-*-SNAPSHOT-jar-with-dependencies.jar config/auth.properties
```

## Contact

For questions: help@reactome.org
