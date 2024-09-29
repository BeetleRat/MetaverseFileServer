package com.metaverse.files.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Hibernate модель представляющая объект Scene.
 *
 * @author Mikhail.Kataranov
 * @since 01.11.2024
 */
@Entity
@Table(name = "scenes")
public class SceneModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sort_index")
    private int sortIndex;
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "device")
    private String device;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "image_file_path")
    private String imageFilePath;

    @OneToMany(mappedBy = "sceneModel", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<HostModel> hostModels;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return индекс используемый для сортировки сцен
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * @param sortIndex индекс используемый для сортировки сцен
     */
    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * @return имя файла сцены
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя файла сцены
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return отображаемое имя сцены
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName отображаемое имя сцены
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return устройство, для которого сделана сцена
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device устройство, для которого сделана сцена
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * @return путь хранения файла сцены на диске
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath путь хранения файла сцены на диске
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return путь хранения файла изображения сцены на диске
     */
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     * @param imageFilePath путь хранения файла изображения сцены на диске
     */
    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    /**
     * @return список {@link HostModel хостов} данной сцены
     */
    public List<HostModel> getHosts() {
        return hostModels;
    }

    /**
     * Добавляет хостов для данной сцены.
     *
     * @param hostModels {@link HostModel хосты} данной сцены
     */
    public void addHosts(HostModel... hostModels) {
        if (this.hostModels == null) {
            this.hostModels = new ArrayList<>();
        }

        for (HostModel hostModel : hostModels) {
            this.hostModels.add(hostModel);
            hostModel.setScene(this);
        }
    }
}
