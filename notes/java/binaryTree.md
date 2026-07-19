# Binary Tree
# Traversals
## DFS
- deep first search (обход в глубину)
- рекурсвный подоход: реализуется через рекурсию
- итеративный подоход: череез стек
1. Pre-order (Прямой обход):
- Корень -> Левый -> Правый
- Копирование дерева, сериализация, префиксные вычисления.
```java
   void preorder(TreeNode root) {
       if (root == null) return;
       // Действие с root.val
       preorder(root.left);
       preorder(root.right);
   }
```

2. In-order (Центрированный обход): 
- Левый -> Корень -> Правый
- если дерево BST то этот обход выдаст отсортированные по возрастанию значения узлов
```java
   void inorder(TreeNode root) {
       if (root == null) return;
       inorder(root.left);
       // Действие с root.val
       inorder(root.right);
   }
```

3. Post-order (Обратный обход): 
- Левый -> Правый -> Корень
- Удаление дерева, вычисление высоты
```java
   void postorder(TreeNode root) {
       if (root == null) return;
       postorder(root.left);
       postorder(root.right);
       // Действие с root.val
   }
```



## BFS 
- Обход в ширину 
- реализуется через очередь
