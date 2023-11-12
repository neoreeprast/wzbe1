-- 1. Tampilkan seluruh data dari table employees
select *
from employees;

-- 2. Berapa banyak karyawan yang memiliki posisi pekerjaan (job title) "Manager"
select *
from employees e 
where e.job_title ='Manager';

-- 3. Tampilkan daftar nama dan gaji (salary) dari karyawan yang bekerja di departemen "Sales" atau "Marketing"
select e."name" as nama, e.salary as gaji
from employees e 
where e.department in ('Sales','Marketing');

-- 4. Hitung rata-rata gaji (salary) dari karyawan yang bergabung (joined) dalam 5 tahun terakhir (berdasarkan kolom "joined_date")
select *
from employees e
where joined_date >= CURRENT_DATE - INTERVAL '5 years';

-- 5. Tampilkan 5 karyawan dengan total penjualan (sales) tertinggi dari tabel "employees" dan "sales_data"
select e.employee_id, e.name, SUM(sd.sales) as total_penjualan
from employees e
join sales_data sd on e.employee_id = sd.employee_id
group by e.employee_id, e.name
order by total_penjualan desc
limit 5;


-- 6. Tampilkan nama, gaji (salary), dan rata-rata gaji (salary) dari semua karyawan yang bekerja di departemen 
-- yang memiliki rata-rata gaji lebih tinggi dari gaji rata-rata di semua departemen

select e."name" , e.salary , b.dept_average_salary, (select avg(salary) from employees e4 ) as average_salary
from employees e 
inner join (
-- average salary per department
select department, avg(e2.salary) as dept_average_salary
			from employees e2 
			group by department
			) b on e.department = b.department
where b.dept_average_salary > (
-- average salary from all employee
select avg(e3.salary) from employees e3
);

-- 7. Tampilkan nama dan total penjualan (sales) dari setiap karyawan, bersama dengan peringkat (ranking) 
-- masing-masing karyawan berdasarkan total penjualan. Peringkat 1 adalah karyawan dengan total
-- penjualan tertinggi
select e.employee_id, e."name" ,sum(sd.sales) as total_penjualan,rank() over (order by sum(sd.sales) desc) AS ranking
from employees e 
inner join sales_data sd on e.employee_id = sd.employee_id 
group by e.employee_id
order by total_penjualan  desc ;

-- 8. Buat sebuah stored procedure yang menerima nama departemen sebagai input, dan mengembalikan
-- daftar karyawan dalam departemen tersebut bersama dengan total gaji (salary) yang mereka terima
create or replace function get_department_employees(dept_name varchar)
RETURNS TABLE(employee_name varchar, total_salary bigint) AS $$
BEGIN
    RETURN QUERY 
    SELECT 
        e.name, 
        SUM(e.salary) AS total_salary
    FROM 
        employees e
    WHERE 
        e.department = dept_name
    GROUP BY 
        e.name;
END;
$$ LANGUAGE plpgsql;

select * from get_department_employees('IT'); 