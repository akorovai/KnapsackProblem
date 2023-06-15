package src.akorovai.bruteforce.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer IdItem;
    private Integer value;
    private Integer weight;
}
