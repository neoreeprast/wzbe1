CREATE TABLE employees (
  employee_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  job_title VARCHAR(255) NOT NULL,
  salary INT NOT NULL,
  department VARCHAR(20) NOT NULL,
  joined_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE sales_data (
  sales_id BIGSERIAL PRIMARY KEY,
  employee_id BIGINT NOT NULL,
  sales INT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);