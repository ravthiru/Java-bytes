# terraform/rds.tf
# Add Random Password Module 
resource "random_password" "rds_password" {
  length = 16
  special = true
  override_special = "_%"
  min_special = 1

}

# # Manage MYSQL RDS  Passwords Using AWS SSM

resource "aws_ssm_parameter" "rds_credentials" {
  name               = "${aws_db_instance.mysql_billing.name}"
  description        = "Credentials for RDS"
  type               = "SecureString"
  value              = "${random_password.rds_password.result}" 
}

# Create and provide Security group details for MYSQL RDS Instance
resource "aws_security_group" "db_security_group" {
  name_prefix = local.workspace["name"]
  vpc_id = local.workspace["vpc_id"]

  ingress {
    from_port   = local.workspace ["port"]
    to_port     = local.workspace ["port"]
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create MYSQL RDS Resource 

resource "aws_db_instance" "mysql_billing" {
  allocated_storage         = local.workspace["allocated_storage"]
  storage_type              = "gp2"
  identifier                = local.workspace["identifier"]
  engine                    = local.workspace["db_engine"]
  engine_version            = local.workspace["db_engine_version"]
  instance_class            = local.workspace["instance_class"]
  name                      = local.workspace["name"]
  username                  = local.workspace["username"]
  password                  = random_password.rds_password.result
  parameter_group_name      = local.workspace["parameter_group_name"]
  vpc_security_group_ids    = ["${aws_security_group.db_security_group.id}"]
  db_subnet_group_name      = local.workspace["subnet_name"]
  multi_az                  = local.workspace["multi_az"]
  port                      = local.workspace["port"]
  skip_final_snapshot       = local.workspace["skip_final_snapshot"]
  backup_retention_period   = local.workspace["backup_retention_period"] 
  backup_window             = local.workspace["backup_window"]  
}
