INSERT INTO projects (id, name, description, status, start_date, end_date)
VALUES
 (1, '社内ポータル刷新', '社内ポータルサイトのリニューアル', 'ACTIVE', '2025-01-01', NULL),
 (2, '勤怠システム改修', '打刻まわりの不具合修正と機能追加', 'ON_HOLD', '2025-02-01', NULL);

INSERT INTO tasks (id, project_id, title, description, assignee, status, priority,
                   due_date, estimated_hours, actual_hours)
VALUES
 (1, 1, '要件定義', '画面・機能の要件整理', '山田', 'IN_PROGRESS', 'HIGH',
  '2025-02-15', 40, 8),
 (2, 1, 'UIデザイン', '主要画面のワイヤーフレーム作成', '佐藤', 'TODO', 'MEDIUM',
  '2025-02-28', 24, 0),
 (3, 2, '打刻API調査', '既存打刻API仕様確認', '田中', 'TODO', 'LOW',
  '2025-03-10', 8, 0);
