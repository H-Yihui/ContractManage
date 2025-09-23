# 后端项目数据模型文档

## 概述

本数据模型文档描述了后端项目中与合同管理相关的核心数据表结构，包括合同、合同模板、条款库以及它们之间的关联关系。此文档旨在为开发人员提供清晰的数据模型参考。

## 表结构详情

### `clause` (条款库)

此表用于存储合同条款的通用模板，便于在不同合同中重复使用。



| **字段名**        | **数据类型**   | **描述**                 |
| ----------------- | -------------- | ------------------------ |
| `clause_id`       | `INT`          | 主键，条款模板的唯一ID。 |
| `title`           | `VARCHAR(200)` | 条款的标题。             |
| `content`         | `TEXT`         | 条款的标准模板内容。     |
| `clause_category` | `VARCHAR(50)`  | 条款的分类。             |

### `contract` (合同)

此表存储实际生成的合同主信息。

| **字段名**      | **数据类型**   | **描述**               |
| --------------- | -------------- | ---------------------- |
| `contract_id`   | `INT`          | 主键，合同的唯一ID。   |
| `contract_name` | `VARCHAR(255)` | 合同的名称。           |
| `created_at`    | `DATETIME`     | 合同创建时间。         |
| `updated_at`    | `DATETIME`     | 合同最近一次更新时间。 |

### `contract_element` (合同元素)

此表存储构成具体合同的各个元素，例如条款、文本、变量等。

| **字段名**         | **数据类型**  | **描述**                                                    |
| ------------------ | ------------- | ----------------------------------------------------------- |
| `element_id`       | `BIGINT`      | 主键，元素的唯一ID。                                        |
| `contract_id`      | `INT`         | 外键，关联的合同ID。                                        |
| `order_index`      | `INT`         | **【新增】** 元素在合同中的显示顺序。数值越小，位置越靠前。 |
| `element_type`     | `VARCHAR(20)` | 元素的类型（如：`TEXT`, `VARIABLE`, `CLAUSE`）。            |
| `content`          | `TEXT`        | 元素的具体内容。                                            |
| `source_clause_id` | `INT`         | 如果元素来源于条款库，则为此条款ID。                        |
| `attributes`       | `JSON`        | 元素的附加属性，以JSON格式存储。                            |

### `contract_template` (合同模板)

此表存储合同模板的基本信息，作为生成合同的蓝图。

| **字段名**      | **数据类型**   | **描述**                                     |
| --------------- | -------------- | -------------------------------------------- |
| `template_id`   | `INT`          | 主键，合同模板的唯一ID。                     |
| `template_name` | `VARCHAR(255)` | 模板的名称。                                 |
| `description`   | `TEXT`         | 模板的详细描述。                             |
| `is_active`     | `TINYINT(1)`   | 模板是否处于激活状态（`1` 为是，`0` 为否）。 |
| `created_at`    | `DATETIME`     | 模板创建时间。                               |
| `updated_at`    | `DATETIME`     | 模板最近一次更新时间。                       |

### `template_element_config` (模板元素配置)

此表定义了合同模板中各个元素的配置和顺序。

| **字段名**           | **数据类型**  | **描述**                                         |
| -------------------- | ------------- | ------------------------------------------------ |
| `config_id`          | `INT`         | 主键，配置项的唯一ID。                           |
| `template_id`        | `INT`         | 外键，关联的合同模板ID。                         |
| `order_index`        | `INT`         | 元素在模板中的排列顺序。                         |
| `element_type`       | `VARCHAR(50)` | 元素的类型。                                     |
| `content_source`     | `VARCHAR(50)` | 内容来源，`STATIC` 或 `CLAUSE_LIBRARY`。         |
| `static_content`     | `TEXT`        | 当内容来源为 `STATIC` 时使用。                   |
| `source_clause_id`   | `INT`         | 当内容来源为 `CLAUSE_LIBRARY` 时，关联的条款ID。 |
| `default_attributes` | `JSON`        | 元素的默认属性。                                 |

## 表间关系

- **`contract` & `contract_element`**: **一对多关系**。一个 `contract` 可以包含多个 `contract_element`。
- **`contract_template` & `template_element_config`**: **一对多关系**。一个 `contract_template` 可以包含多个 `template_element_config`。
- **`clause` & `template_element_config`**: **一对多关系**。一个 `clause` 可以在多个 `template_element_config` 中被引用。
- **`clause` & `contract_element`**: **一对多关系**。一个 `clause` 可以在多个 `contract_element` 中被引用。