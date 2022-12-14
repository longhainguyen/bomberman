# Bomberman

## Thông tin chung:
**Tên nhóm: Nhóm 25**
| Họ và tên     | Mã sinh viên |
| ------------- | ------------ |
| Trần Quý Mạnh | 21020352     |
| Nguyễn Hải Long  | 21020349     |
     
## Mô tả về các đối tượng trò chơi:
- ![](res/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 
- ![](res/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](res/sprites/explosion_horizontal.png) được tạo ra.


- ![](res/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](res/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.


- ![](res/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- ![](res/sprites/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- ![](res/sprites/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
- ![](res/sprites/powerup_wallpass.png) *Wallpass* Khi sử dụng Item này, Bomber sẽ được đi xuyên qua các tường gạch trong thời gian nhất định
- ![](res/sprites/powerup_bombpass.png) *Bombpass* Khi sử dụng Item này, Bomber sẽ được đi qua bomb trong thời gian nhất định
- ![](res/sprites/powerup_detonator.png) *Control* Khi sử dụng Item này, Bomber sẽ điểu khiển được bomb nổ bất cứ lúc nào, khi hết hiệu lực bomb sẽ tự nổ
- ![](res/sprites/powerup_flamepass.png) *Survival* Khi sử dụng Item này, Bomber sẽ sống sót qua vụ nổ bomb và tấn công của enemies

**Thông tin về các loại *Enemy* được liệt kê như dưới đây:**

- ![](res/sprites/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc 32 pixels / 64 khung hình, không thể đi xuyên bomb.
- ![](res/sprites/oneal_left1.png) *Oneal* có tốc độ di chuyển đuổi theo bomber (sử dụng thuật toán AI) khi bomber nằm trong phạm vi của nó.
- ![](res/sprites/doll_left1.png) *Doll* Là Enemy di chuyển ngẫu nhiên lúc nhanh lúc chậm.
- ![](res/sprites/kondoria_right1.png) *Kondoria* Là Enemy có tốc độ di chuyển đuổi theo bomber (sử dụng thuật toán AI) và có khả năng đi qua gạch nhưng di chuyển chậm.
- ![](res/sprites/minvo_left1.png) *Minvo* là Enemy đơn giản , có lúc di chuyển có lúc dừng lại.
- ![](res/sprites/Ovape.png) *Ovape* là Enemy tốc độ thay đổi liên tục.
- ![](res/sprites/Pontan.png) *Pontan* là Enemy có khả năng đi xuyên tường.
- ![](res/sprites/Pass.png) *Pass* là Enemy lúc di chuyển lúc đứng yên.
- ![](res/sprites/Minva.png) *Minva* là Enemy có nhiều khả năng khi gần bomber sẽ tìm và đi xuyên tường, không sẽ di chuyển ngẫu nhiên.

## Mô tả game
### Điều khiển

- Đối với Bomber sử dụng các phím `mũi tên` tương ứng với đi lên, trái, xuống, phải. `Space` để đặt bomb.
- Khi chơi người chơi có thể tùy chọn bài hát, `bật/tắt` âm thanh, hiệu ứng.
- Khi đang trong chế độ chơi có thể nhấn `Esc` để tạm dừng trò chơi hoặc có thể chơi lại với việc có thể lựa chọn lại level phù hợp.

### Cơ chế

- Bomber sẽ có 3 mạng chơi, khi va chạm với enemies hoặc bị bomb nổ bomber sẽ bị `trừ` đi một mạng.
- Bomber được buff nhiều loại sức mạnh khác nhau khi ăn các `Item` bằng việc phá hủy công trình.
- Người chơi `thắng` khi giết được tất cả các enemies và tìm đường vào `portal`.
- Người chơi `thua` khi số mạng về `0` hoặc `hết thời gian`.
### Cây thừa kế
- ![](res/UML/bomberman.drawio.png) 
 